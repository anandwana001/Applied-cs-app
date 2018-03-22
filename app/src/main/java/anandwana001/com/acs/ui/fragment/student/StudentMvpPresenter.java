package anandwana001.com.acs.ui.fragment.student;

import anandwana001.com.acs.base.MvpPresenter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by
 * anandwana001 on
 * 22-03-2018 at
 * 09:30 AM.
 */
public interface StudentMvpPresenter<V extends StudentMvpView> extends MvpPresenter<V> {

  void fetchRecyclerData(RecyclerView recyclerView);
}
