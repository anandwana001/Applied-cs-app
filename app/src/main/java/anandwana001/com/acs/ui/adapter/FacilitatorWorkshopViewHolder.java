package anandwana001.com.acs.ui.adapter;

import anandwana001.com.acs.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by
 * dell on
 * 17-01-2018 at
 * 07:57 AM.
 */

public class FacilitatorWorkshopViewHolder extends RecyclerView.ViewHolder {

  public View view;

  public FacilitatorWorkshopViewHolder(View itemView) {
    super(itemView);
    view = itemView;
  }
  public void setFacilitatorWorkshopName(String userName){
    TextView username = (TextView) view.findViewById(R.id.facilitator_workshop_name);
    username.setText(userName);
  }
  public void setFacilitatorWorkshopId(String userName){
    TextView username = (TextView) view.findViewById(R.id.facilitator_workshop_id);
    username.setText("ID: "+userName);
  }
}
