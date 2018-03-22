package anandwana001.com.acs.ui.login;

import anandwana001.com.acs.base.MvpPresenter;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 08:56 PM.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

  void checkUserExist();
}
