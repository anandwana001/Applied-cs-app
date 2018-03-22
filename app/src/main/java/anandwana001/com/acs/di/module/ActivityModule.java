package anandwana001.com.acs.di.module;

import anandwana001.com.acs.di.ActivityContext;
import anandwana001.com.acs.di.PerActivity;
import anandwana001.com.acs.ui.fragment.about.AboutMvpPresenter;
import anandwana001.com.acs.ui.fragment.about.AboutMvpView;
import anandwana001.com.acs.ui.fragment.about.AboutPresenter;
import anandwana001.com.acs.ui.fragment.student.StudentMvpPresenter;
import anandwana001.com.acs.ui.fragment.student.StudentMvpView;
import anandwana001.com.acs.ui.fragment.student.StudentPresenter;
import anandwana001.com.acs.ui.login.LoginMvpPresenter;
import anandwana001.com.acs.ui.login.LoginMvpView;
import anandwana001.com.acs.ui.login.LoginPresenter;
import anandwana001.com.acs.ui.main.MainMvpPresenter;
import anandwana001.com.acs.ui.main.MainMvpView;
import anandwana001.com.acs.ui.main.MainPresenter;
import anandwana001.com.acs.ui.setupaccount.SetupMvpPresenter;
import anandwana001.com.acs.ui.setupaccount.SetupMvpView;
import anandwana001.com.acs.ui.setupaccount.SetupPresenter;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 08:45 PM.
 */
@Module
public class ActivityModule {

  private AppCompatActivity mActivity;

  public ActivityModule(AppCompatActivity activity) {
    this.mActivity = activity;
  }

  @Provides
  @ActivityContext
  Context provideContext() {
    return mActivity;
  }

  @Provides
  AppCompatActivity provideActivity() {
    return mActivity;
  }

  @Provides
  @PerActivity
  LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
      LoginPresenter<LoginMvpView> presenter) {
    return presenter;
  }

  @Provides
  @PerActivity
  SetupMvpPresenter<SetupMvpView> provideSetupPresenter(
      SetupPresenter<SetupMvpView> presenter) {
    return presenter;
  }

  @Provides
  @PerActivity
  MainMvpPresenter<MainMvpView> provideMainPresenter(
      MainPresenter<MainMvpView> presenter) {
    return presenter;
  }

  @Provides
  AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
      AboutPresenter<AboutMvpView> presenter) {
    return presenter;
  }

  @Provides
  StudentMvpPresenter<StudentMvpView> provideStudentPresenter(
      StudentPresenter<StudentMvpView> presenter) {
    return presenter;
  }
  @Provides
  LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
    return new LinearLayoutManager(activity);
  }
}