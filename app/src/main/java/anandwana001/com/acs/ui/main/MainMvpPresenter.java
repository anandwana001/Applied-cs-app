package anandwana001.com.acs.ui.main;

import anandwana001.com.acs.base.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;

/**
 * Created by
 * dell on
 * 06-02-2018 at
 * 07:55 PM.
 */

public interface MainMvpPresenter <V extends MainMvpView> extends MvpPresenter<V> {

  AuthStateListener authCheck(FirebaseAuth firebaseAuth);
  void signOut();
}
