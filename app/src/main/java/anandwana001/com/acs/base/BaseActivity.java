package anandwana001.com.acs.base;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.R;
import anandwana001.com.acs.di.component.ActivityComponent;
import anandwana001.com.acs.di.component.DaggerActivityComponent;
import anandwana001.com.acs.di.module.ActivityModule;
import anandwana001.com.acs.utils.CommonUtils;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Unbinder;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 09:14 PM.
 */
public abstract class BaseActivity extends AppCompatActivity
    implements MvpView, BaseFragment.Callback {

  private ActivityComponent mActivityComponent;
  private Unbinder mUnBinder;
  private ProgressDialog mProgressDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityComponent = DaggerActivityComponent.builder()
        .activityModule(new ActivityModule(this))
        .applicationComponent(((AcsApplication) getApplication()).getComponent())
        .build();
  }

  public ActivityComponent getActivityComponent() {
    return mActivityComponent;
  }

  public void setUnBinder(Unbinder unBinder) {
    mUnBinder = unBinder;
  }

  private void showSnackBar(String message) {
    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
        message, Snackbar.LENGTH_SHORT);
    View sbView = snackbar.getView();
    TextView textView = (TextView) sbView
        .findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(ContextCompat.getColor(this, R.color.white));
    snackbar.show();
  }

  @Override
  protected void onDestroy() {

    if (mUnBinder != null) {
      mUnBinder.unbind();
    }
    super.onDestroy();
  }

  @Override
  public void showLoading(String message) {
    mProgressDialog = CommonUtils.showLoadingDialog(this, message);
  }

  @Override
  public void hideLoading() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.cancel();
    }
  }

  @Override
  public void showMessage(String message) {
    if (message != null) {
      Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onError(String message) {
    if (message != null) {
      showSnackBar(message);
    }
  }
  protected abstract void setUp();
}
