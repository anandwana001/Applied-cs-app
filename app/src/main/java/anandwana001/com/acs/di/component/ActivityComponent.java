package anandwana001.com.acs.di.component;

import anandwana001.com.acs.di.PerActivity;
import anandwana001.com.acs.di.module.ActivityModule;
import anandwana001.com.acs.ui.fragment.about.AboutFragment;
import anandwana001.com.acs.ui.fragment.student.StudentFragment;
import anandwana001.com.acs.ui.login.LoginActivityNew;
import anandwana001.com.acs.ui.main.MainActivity;
import anandwana001.com.acs.ui.setupaccount.SetupAccountActivity;
import dagger.Component;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 08:49 PM.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(LoginActivityNew activity);
  void inject(SetupAccountActivity setupAccountActivity);
  void inject(MainActivity mainActivity);
  void inject(AboutFragment aboutFragment);
  void inject(StudentFragment studentFragment);
}