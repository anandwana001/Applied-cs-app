package anandwana001.com.acs.ui.setupaccount;

import anandwana001.com.acs.base.MvpPresenter;

/**
 * Created by
 * dell on
 * 10-01-2018 at
 * 08:27 AM.
 */

public interface SetupMvpPresenter<V extends SetupMvpView> extends MvpPresenter<V> {

  void setupAccountOnFirebase(String name, String college_name);
}
