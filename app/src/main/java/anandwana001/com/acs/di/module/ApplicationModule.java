package anandwana001.com.acs.di.module;

import anandwana001.com.acs.di.ApplicationContext;
import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 08:43 PM.
 */
@Module
public class ApplicationModule {

  private final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides
  @ApplicationContext
  Context provideContext() {
    return mApplication;
  }

  @Provides
  Application provideApplication() {
    return mApplication;
  }


}
