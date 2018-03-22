package anandwana001.com.acs.ui.fragment.student;

import anandwana001.com.acs.base.MvpView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

/**
 * Created by
 * anandwana001 on
 * 22-03-2018 at
 * 09:30 AM.
 */

public interface StudentMvpView extends MvpView {

  void joinWorkshop(String uniqueId);
  void setAdapterData(FirebaseRecyclerAdapter adapterData);
}
