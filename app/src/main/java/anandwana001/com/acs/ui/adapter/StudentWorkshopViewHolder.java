package anandwana001.com.acs.ui.adapter;

import anandwana001.com.acs.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by
 * dell on
 * 17-01-2018 at
 * 09:30 AM.
 */

public class StudentWorkshopViewHolder extends RecyclerView.ViewHolder {

  public View view;

  public StudentWorkshopViewHolder(View itemView) {
    super(itemView);
    view = itemView;
  }
  public void setStudentWorkshopName(String userName){
    TextView username = (TextView) view.findViewById(R.id.student_workshop_name);
    username.setText(userName);
  }

}
