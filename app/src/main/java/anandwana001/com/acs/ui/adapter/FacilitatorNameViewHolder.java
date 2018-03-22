package anandwana001.com.acs.ui.adapter;

import anandwana001.com.acs.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by
 * anandwana001 on
 * 20-03-2018 at
 * 06:40 PM.
 */

public class FacilitatorNameViewHolder extends RecyclerView.ViewHolder {

  public View view;
  public FacilitatorNameViewHolder(View itemView) {
    super(itemView);
    view = itemView;
  }
  public void setFacilitatorName(String userName){
    TextView username = (TextView) view.findViewById(R.id.facilitator_workshop_name);
    username.setText(userName);
  }

}
