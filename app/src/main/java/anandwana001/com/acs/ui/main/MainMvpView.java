package anandwana001.com.acs.ui.main;

import anandwana001.com.acs.base.MvpView;

/**
 * Created by
 * dell on
 * 06-02-2018 at
 * 07:55 PM.
 */

public interface MainMvpView extends MvpView {

  void openLoginActivity();

  void showProjectFragment();
  void showLearFragment();
  void showFacilitatorFragment();
  void showStudentFragment();
  void showAboutFragment();

  void closeNavigationDrawer();
  void lockDrawer();
  void unlockDrawer();
}
