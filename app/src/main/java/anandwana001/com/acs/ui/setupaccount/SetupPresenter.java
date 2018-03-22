package anandwana001.com.acs.ui.setupaccount;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.base.BasePresenter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 10-01-2018 at
 * 08:27 AM.
 */

public class SetupPresenter<V extends SetupMvpView> extends BasePresenter<V>
    implements SetupMvpPresenter<V> {

  @Inject
  public SetupPresenter(){

  }

  @Override
  public void setupAccountOnFirebase(String name,final String college_name) {
    if (name == null || name.isEmpty()) {
      getMvpView().onError("empty name");
      return;
    }
    if (college_name == null || college_name.isEmpty()) {
      getMvpView().onError("empty_password");
      return;
    }
    getMvpView().showLoading("Making Account");

    final String uid = AcsApplication.getInstanceAuth().getCurrentUser().getUid();
    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
    databaseReference.child(uid).child("name").setValue(name).addOnSuccessListener(
        new OnSuccessListener<Void>() {

          @Override
          public void onSuccess(Void aVoid) {

            databaseReference.child(uid).child("college_name").setValue(college_name).addOnSuccessListener(
                new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {
                    getMvpView().hideLoading();
                    getMvpView().openMainActivityFromSetup();
                  }
                }
            );

          }
        }
    );
  }
}