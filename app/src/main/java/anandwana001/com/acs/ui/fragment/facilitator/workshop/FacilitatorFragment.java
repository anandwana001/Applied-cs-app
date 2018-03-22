package anandwana001.com.acs.ui.fragment.facilitator.workshop;

import static anandwana001.com.acs.utils.CommonUtils.TAG_FAC;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.R;
import anandwana001.com.acs.ui.adapter.FacilitatorWorkshopViewHolder;
import anandwana001.com.acs.ui.facilitator.FacilitatorActivity;
import anandwana001.com.acs.ui.model.FacilitatorWorkshopModel;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

/**
 * Created by
 * dell on
 * 13-01-2018 at
 * 09:02 AM.
 */

public class FacilitatorFragment extends Fragment {

  Unbinder unbinder;
  @BindView(R.id.recycler_view_project)
  RecyclerView workshops;
  @BindView(R.id.fab)
  FloatingActionButton fab;
  @BindView(R.id.no_workshop)
  TextView noWorkshop;

  private static final int SPAN_COUNT = 2;
  private String KEY_LAYOUT_MANAGER = "list_state";

  private enum LayoutManagerType {
    GRID_LAYOUT_MANAGER,
    LINEAR_LAYOUT_MANAGER
  }

  protected LayoutManagerType mCurrentLayoutManagerType;
  protected LayoutManager mLayoutManager;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.content_facilitator, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    mLayoutManager = new LinearLayoutManager(getContext());
    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

    if (savedInstanceState != null) {
      mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(
          KEY_LAYOUT_MANAGER);
    }
    setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

    FirebaseRecyclerAdapter<FacilitatorWorkshopModel, FacilitatorWorkshopViewHolder> eventEventViewHolderFirebaseRecyclerAdapter
        = new FirebaseRecyclerAdapter<FacilitatorWorkshopModel, FacilitatorWorkshopViewHolder>(
        FacilitatorWorkshopModel.class,
        R.layout.content_facilitator_workshop,
        FacilitatorWorkshopViewHolder.class,
        FirebaseDatabase.getInstance().getReference().child("workshop")
            .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
    ) {
      @Override
      protected void populateViewHolder(FacilitatorWorkshopViewHolder viewHolder,
          final FacilitatorWorkshopModel model, final int position) {
        viewHolder.setFacilitatorWorkshopName(model.getWorkshop_name());
        viewHolder.setFacilitatorWorkshopId(model.getWorkshop_id());

        viewHolder.view.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(getContext(), FacilitatorActivity.class);
            intent.putExtra("workshop_id", model.getWorkshop_id());
            startActivity(intent);
          }
        });
      }
    };
    workshops.setAdapter(eventEventViewHolderFirebaseRecyclerAdapter);

    fab.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        onClickFloatingAdditionButton();
      }
    });

    return rootView;
  }

  public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
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
  }

  private void onClickFloatingAdditionButton() {
    final Builder dialogBuilder = new Builder(getContext());
    LayoutInflater inflater = this.getLayoutInflater();
    final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
    dialogBuilder.setView(dialogView);

    final TextInputEditText emailInput = dialogView.findViewById(R.id.workshop_name);
    final TextInputEditText uniqueCode = dialogView.findViewById(R.id.unique_code);
    final TextInputEditText facilitatorCode = dialogView.findViewById(R.id.fac_code);

    dialogBuilder.setIcon(R.drawable.ic_add_black_24dp);
    dialogBuilder.setTitle(R.string.create_workshop);
    dialogBuilder.setPositiveButton(R.string.create_button, new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        setupWorkshop(emailInput.getText().toString(), uniqueCode.getText().toString(),
            facilitatorCode.getText().toString());
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

  private void setupWorkshop(final String email, final String uniqueId,
      final String facilitatorCode) {
    if (email == null || email.isEmpty()) {
      showSnackBar("empty email");
      return;
    }
    if (uniqueId == null || uniqueId.isEmpty()) {
      showSnackBar("empty unique id");
      return;
    }
    if (facilitatorCode == null || facilitatorCode.isEmpty()) {
      showSnackBar("empty facilitator id");
      return;
    }

    final HashMap<String, String> datamap = new HashMap<String, String>();
    final HashMap<String, String> datamap_workshop_all = new HashMap<String, String>();
    datamap.put("mentor_id", facilitatorCode);
    datamap.put("workshop_id", uniqueId);
    datamap.put("workshop_name", email);
    datamap_workshop_all.put("mentor_id", facilitatorCode);
    datamap_workshop_all.put("workshop_name", email);

    FirebaseDatabase.getInstance().getReference()
        .child("mentor_id")
        .addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.hasChild(facilitatorCode)) {
              FirebaseDatabase.getInstance().getReference()
                  .child("workshop")
                  .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
                  .child(uniqueId)
                  .setValue(datamap)
                  .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()) {
                        FirebaseDatabase.getInstance().getReference()
                            .child("workshop_all_id")
                            .child(uniqueId)
                            .setValue(datamap_workshop_all);
                      } else {
                        showSnackBar("Error");
                      }
                    }
                  });
            }
          }
          @Override
          public void onCancelled(DatabaseError databaseError) {
          }
        });
  }

  private void showSnackBar(String message) {
    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
        message, Snackbar.LENGTH_SHORT);
    View sbView = snackbar.getView();
    TextView textView = sbView
        .findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    snackbar.show();
  }

  @Override
  public void onResume() {
    super.onResume();
    getActivity().setTitle(TAG_FAC);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
    super.onSaveInstanceState(savedInstanceState);
  }
}
