package anandwana001.com.acs.ui.adapter;

import anandwana001.com.acs.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by
 * dell on
 * 17-01-2018 at
 * 10:55 AM.
 */

public class FacilitatorStudentViewHolder extends RecyclerView.ViewHolder {

  public View view;
  public FacilitatorStudentViewHolder(View itemView) {
    super(itemView);
    view = itemView;
  }
  public void setFacilitatorStudentName(String userName){
    TextView username = (TextView) view.findViewById(R.id.facilitator_workshop_name);
    username.setText(userName);
  }
}
