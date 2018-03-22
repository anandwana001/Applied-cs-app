package anandwana001.com.acs.ui.main;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.base.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 06-02-2018 at
 * 07:56 PM.
 */

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V>
    implements MainMvpPresenter<V> {

  @Inject
  public MainPresenter(){

  }

  @Override
  public void onDrawerOptionAboutClick() {
    getMvpView().closeNavigationDrawer();
    getMvpView().showAboutFragment();
  }

  @Override
  public void onDrawerOptionLogoutClick() {
    getMvpView().showLoading("Log out");

  }

  @Override
  public void onDrawerOptionProjectClick() {
    getMvpView().closeNavigationDrawer();
    getMvpView().showProjectFragment();
  }

  @Override
  public void onDrawerOptionLearnClick() {
    getMvpView().closeNavigationDrawer();
    getMvpView().showLearFragment();
  }

  @Override
  public void onDrawerOptionFacilitatorClick() {
    getMvpView().closeNavigationDrawer();
    getMvpView().showFacilitatorFragment();
  }

  @Override
  public void onDrawerOptionStudentClick() {
    getMvpView().closeNavigationDrawer();
    getMvpView().showStudentFragment();
  }


  @Override
  public void onNavMenuCreated() {
    if (!isViewAttached()) {
      return;
    }
  }

  @Override
  public AuthStateListener checkFirebaseAuth() {
    AuthStateListener mAuthListener = new AuthStateListener() {
      @Override
      public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
        if (AcsApplication.getInstanceAuth().getCurrentUser() == null) {
          getMvpView().openLoginActivity();
        }
      }
    };
    return mAuthListener;
  }


}
