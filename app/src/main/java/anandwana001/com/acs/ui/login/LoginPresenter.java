package anandwana001.com.acs.ui.login;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.base.BasePresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 09:00 PM.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
    implements LoginMvpPresenter<V> {

  @Inject
  public LoginPresenter() {
  }

  @Override
  public void checkUserExist() {

    if (AcsApplication.getInstanceAuth().getCurrentUser() != null) {
      final String user_id = AcsApplication.getInstanceAuth().getCurrentUser().getUid();
      FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

          if (!dataSnapshot.hasChild(user_id)) {
            getMvpView().openSetupAccountActivity();
            getMvpView().hideLoading();
          } else {
            getMvpView().openMainActivity();
            getMvpView().hideLoading();
          }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
      });
    }
  }
}