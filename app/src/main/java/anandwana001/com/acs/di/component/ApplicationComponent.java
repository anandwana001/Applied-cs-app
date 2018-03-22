package anandwana001.com.acs.di.component;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.di.ApplicationContext;
import anandwana001.com.acs.di.module.ApplicationModule;
import android.app.Application;
import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 08:48 PM.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(AcsApplication acsApplication);

  @ApplicationContext
  Context context();

  Application application();

}
