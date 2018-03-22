package anandwana001.com.acs;

import anandwana001.com.acs.di.component.ApplicationComponent;
import anandwana001.com.acs.di.component.DaggerApplicationComponent;
import anandwana001.com.acs.di.module.ApplicationModule;
import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by
 dell on
 09-01-2018 at
 09:28 AM.
 */

public class AcsApplication extends Application {

  private static FirebaseAuth mFirebaseAuth;

  public static FirebaseAuth getInstanceAuth() {
    return AcsApplication.mFirebaseAuth;
  }

  private ApplicationComponent mApplicationComponent;
  private static AcsApplication instance;

  public static AcsApplication getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;

    mApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();

    mApplicationComponent.inject(this);

    AcsApplication.mFirebaseAuth = FirebaseAuth.getInstance();

    if (!FirebaseApp.getApps(this).isEmpty()) {
      FirebaseDatabase.getInstance().setPersistenceEnabled(true);
      FirebaseDatabase.getInstance().getReference().keepSynced(true);
    }
  }

  public ApplicationComponent getComponent() {
    return mApplicationComponent;
  }

  public void setComponent(ApplicationComponent applicationComponent) {
    mApplicationComponent = applicationComponent;
  }

}
