package anandwana001.com.acs.ui.fragment.student;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.R;
import anandwana001.com.acs.base.BasePresenter;
import anandwana001.com.acs.ui.adapter.StudentWorkshopViewHolder;
import anandwana001.com.acs.ui.model.StudentWorkshopModel;
import android.support.v7.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import javax.inject.Inject;

/**
 * Created by
 * anandwana001 on
 * 22-03-2018 at
 * 09:30 AM.
 */

public class StudentPresenter<V extends StudentMvpView> extends BasePresenter<V>
      implements StudentMvpPresenter<V>{

  @Inject
  public StudentPresenter(){
    super();
  }

  @Override
  public void fetchRecyclerData(RecyclerView recyclerView){
    FirebaseRecyclerAdapter<StudentWorkshopModel, StudentWorkshopViewHolder> eventEventViewHolderFirebaseRecyclerAdapter
        = new FirebaseRecyclerAdapter<StudentWorkshopModel, StudentWorkshopViewHolder>(
        StudentWorkshopModel.class,
        R.layout.content_student_workshop,
        StudentWorkshopViewHolder.class,
        FirebaseDatabase.getInstance().getReference().child("student_attend_workshop")
            .child(AcsApplication.getInstanceAuth().getCurrentUser().getUid())
    ) {
      @Override
      protected void populateViewHolder(StudentWorkshopViewHolder viewHolder, final StudentWorkshopModel model, final int position) {
        viewHolder.setStudentWorkshopName(model.getWorkshop_name());
      }
    };
    getMvpView().setAdapterData(eventEventViewHolderFirebaseRecyclerAdapter);
  }


}
