package anandwana001.com.acs.ui.main;

import anandwana001.com.acs.base.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
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
  public AuthStateListener authCheck(FirebaseAuth firebaseAuth) {
    final FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
    AuthStateListener mAuthListener = new AuthStateListener() {
      @Override
      public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
        if (mFirebaseUser == null) {
          getMvpView().openLoginActivity();
        }
      }
    };
    return mAuthListener;
  }

  @Override
  public void signOut() {
    FirebaseAuth.getInstance().signOut();
    getMvpView().openLoginActivity();
  }
}
