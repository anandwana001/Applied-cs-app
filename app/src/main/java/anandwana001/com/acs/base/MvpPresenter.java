package anandwana001.com.acs.base;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 08:57 PM.
 */

public interface MvpPresenter<V extends MvpView> {

  void onAttach(V mvpView);
  void onDetach();
  void setUserAsLoggedOut();
}
