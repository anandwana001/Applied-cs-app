package anandwana001.com.acs.ui.fragment.student;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.R;
import anandwana001.com.acs.base.BaseFragment;
import anandwana001.com.acs.di.component.ActivityComponent;
import anandwana001.com.acs.ui.main.MainActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 13-01-2018 at
 * 09:02 AM.
 */

public class StudentFragment extends BaseFragment implements StudentMvpView {

  @Inject
  StudentMvpPresenter<StudentMvpView> mPresenter;

  @Inject
  LinearLayoutManager mLayoutManager;

  @BindView(R.id.recycler_view_project)
  RecyclerView workshops;
  @BindView(R.id.fab)
  FloatingActionButton fab;
  @BindView(R.id.no_workshop)
  TextView noWorkshop;


  @Override
  protected void setUp(View view) {
    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    workshops.setLayoutManager(mLayoutManager);
    workshops.setItemAnimator(new DefaultItemAnimator());
    mPresenter.fetchRecyclerData(workshops);
  }

  public static StudentFragment newInstance() {
    Bundle args = new Bundle();
    StudentFragment fragment = new StudentFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private enum LayoutManagerType {
    GRID_LAYOUT_MANAGER,
    LINEAR_LAYOUT_MANAGER
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.content_facilitator, container, false);

    ActivityComponent component = getActivityComponent();
    if (component != null) {
      component.inject(this);
      setUnBinder(ButterKnife.bind(this, rootView));
      mPresenter.onAttach(this);
    }

    return rootView;
  }

 /* public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
    int scrollPosition = 0;

    if (workshops.getLayoutManager() != null) {
      scrollPosition = ((LinearLayoutManager) workshops.getLayoutManager())
          .findFirstCompletelyVisibleItemPosition();
    }
    switch (layoutManagerType) {
      case GRID_LAYOUT_MANAGER:
        mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        break;
      case LINEAR_LAYOUT_MANAGER:
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        break;
      default:
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }
    workshops.setLayoutManager(mLayoutManager);
    workshops.scrollToPosition(scrollPosition);
  }*/

  @OnClick(R.id.fab)
  public void fabShowDialogClick() {
    final Builder dialogBuilder = new Builder(getContext());
    LayoutInflater inflater = this.getLayoutInflater();
    final View dialogView = inflater.inflate(R.layout.custom_dialog_student, null);
    dialogBuilder.setView(dialogView);

    final TextInputEditText uniqueCode = dialogView.findViewById(R.id.unique_id_match);

    dialogBuilder.setIcon(R.drawable.ic_add_black_24dp);
    dialogBuilder.setTitle(R.string.join_workshop);
    dialogBuilder.setPositiveButton(R.string.join_button, new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        joinWorkshop(uniqueCode.getText().toString());
      }
    });
    dialogBuilder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    AlertDialog b = dialogBuilder.create();
    b.show();
  }

  @Override
  public void joinWorkshop(final String uniqueId) {
    if (uniqueId == null || uniqueId.isEmpty()) {
      showSnackBar("empty unique id");
      return;
    }

    FirebaseDatabase.getInstance().getReference().child("workshop_all_id").addListenerForSingleValueEvent(
        new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.hasChild(uniqueId)) {

              FirebaseDatabase.getInstance().getReference().child("attende_list")
                  .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                  if(dataSnapshot.hasChild(uniqueId)){

                    for(DataSnapshot data: dataSnapshot.child(uniqueId).getChildren()){

                      if (data.child("student_id").getValue()
                          .equals(AcsApplication.getInstanceAuth().getCurrentUser().getUid())) {

                        Toast.makeText(getContext(),"Already Joined",Toast.LENGTH_LONG).show();

                      } else {

                        FirebaseDatabase.getInstance().getReference().child("student_attend_workshop")
                            .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
                            .child(uniqueId)
                            .child("workshop_name")
                            .setValue(dataSnapshot.child(uniqueId).child("workshop_name").getValue().toString()).addOnSuccessListener(
                            new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void aVoid) {

                                FirebaseDatabase.getInstance().getReference().child("student_attend_workshop")
                                    .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
                                    .child(uniqueId)
                                    .child("workshop_id")
                                    .setValue(uniqueId).addOnSuccessListener(

                                    new OnSuccessListener<Void>() {
                                      @Override
                                      public void onSuccess(Void aVoid) {

                                        FirebaseDatabase.getInstance().getReference().child("Users")
                                            .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
                                            .child("name")
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(DataSnapshot dataSnapshot) {

                                                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("attende_list")
                                                    .child(uniqueId)
                                                    .push();

                                                databaseReference.child("name")
                                                    .setValue(dataSnapshot.getValue()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                  @Override
                                                  public void onSuccess(Void aVoid) {
                                                    databaseReference.child("student_id")
                                                        .setValue(AcsApplication.getInstanceAuth().getCurrentUser().getUid());
                                                  }
                                                });
                                              }

                                              @Override
                                              public void onCancelled(DatabaseError databaseError) {

                                              }
                                            });
                                      }
                                    });
                              }
                            });
                      }
                    }

                  }else{

                    final String[] workshop_name = new String[1];
                    FirebaseDatabase.getInstance().getReference()
                        .child("workshop_all_id")
                        .child(uniqueId)
                        .child("workshop_name").addListenerForSingleValueEvent(

                        new ValueEventListener() {
                          @Override
                          public void onDataChange(DataSnapshot dataSnapshot) {
                            workshop_name[0] = dataSnapshot.getValue().toString();

                            FirebaseDatabase.getInstance().getReference().child("student_attend_workshop")
                                .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
                                .child(uniqueId)
                                .child("workshop_name")
                                .setValue(workshop_name[0]).addOnSuccessListener(
                                    new OnSuccessListener<Void>() {
                                      @Override
                                      public void onSuccess(Void aVoid) {

                                        FirebaseDatabase.getInstance().getReference().child("student_attend_workshop")
                                            .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
                                            .child(uniqueId)
                                            .child("workshop_id")
                                            .setValue(uniqueId).addOnSuccessListener(

                                            new OnSuccessListener<Void>() {
                                              @Override
                                              public void onSuccess(Void aVoid) {

                                                FirebaseDatabase.getInstance().getReference().child("Users")
                                                    .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
                                                    .child("name")
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(DataSnapshot dataSnapshot) {

                                                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("attende_list")
                                                            .child(uniqueId)
                                                            .push();

                                                        databaseReference.child("name")
                                                            .setValue(dataSnapshot.getValue()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                          @Override
                                                          public void onSuccess(Void aVoid) {
                                                            databaseReference.child("student_id")
                                                                .setValue(AcsApplication.getInstanceAuth().getCurrentUser().getUid());
                                                          }
                                                        });
                                                      }

                                                      @Override
                                                      public void onCancelled(DatabaseError databaseError) {

                                                      }
                                                    });
                                              }
                                            });
                                      }
                                    });
                          }

                          @Override
                          public void onCancelled(DatabaseError databaseError) {

                          }
                        });

                  }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
              });
            }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
          }
        });
  }

  @Override
  public void setAdapterData(FirebaseRecyclerAdapter adapterData) {
    workshops.setAdapter(adapterData);
  }

  private void showSnackBar(String message) {
    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
        message, Snackbar.LENGTH_SHORT);
    View sbView = snackbar.getView();
    TextView textView = (TextView) sbView
        .findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    snackbar.show();
  }

  @Override
  public void onResume() {
    super.onResume();
    getActivity().setTitle(MainActivity.TAG_STU);
  }

  @Override
  public void onDestroyView() {
    mPresenter.onDetach();
    super.onDestroyView();
  }

 /* @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
    super.onSaveInstanceState(savedInstanceState);
  }*/
}
