package anandwana001.com.acs.ui.main;

import anandwana001.com.acs.base.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;

/**
 * Created by
 * dell on
 * 06-02-2018 at
 * 07:55 PM.
 */

public interface MainMvpPresenter <V extends MainMvpView> extends MvpPresenter<V> {

  void onDrawerOptionAboutClick();

  void onDrawerOptionLogoutClick();

  void onDrawerOptionProjectClick();

  void onDrawerOptionLearnClick();

  void onDrawerOptionFacilitatorClick();

  void onDrawerOptionStudentClick();

  void onNavMenuCreated();

  AuthStateListener checkFirebaseAuth();
}
