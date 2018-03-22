package anandwana001.com.acs.base;

import anandwana001.com.acs.di.component.ActivityComponent;
import anandwana001.com.acs.utils.CommonUtils;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import butterknife.Unbinder;

/**
 * Created by
 * anandwana001 on
 * 21-03-2018 at
 * 10:30 AM.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

  private BaseActivity mActivity;
  private Unbinder mUnBinder;
  private ProgressDialog mProgressDialog;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUp(view);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof BaseActivity) {
      BaseActivity activity = (BaseActivity) context;
      this.mActivity = activity;
      activity.onFragmentAttached();
    }
  }

  @Override
  public void showLoading(String message) {
    hideLoading();
    mProgressDialog = CommonUtils.showLoadingDialog(this.getContext(),message);
  }

  @Override
  public void hideLoading() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.cancel();
    }
  }

  @Override
  public void onError(String message) {
    if (mActivity != null) {
      mActivity.onError(message);
    }
  }

  @Override
  public void showMessage(String message) {
    if (mActivity != null) {
      mActivity.showMessage(message);
    }
  }

  @Override
  public void onDetach() {
    mActivity = null;
    super.onDetach();
  }

  public ActivityComponent getActivityComponent() {
    if (mActivity != null) {
      return mActivity.getActivityComponent();
    }
    return null;
  }

  public BaseActivity getBaseActivity() {
    return mActivity;
  }

  public void setUnBinder(Unbinder unBinder) {
    mUnBinder = unBinder;
  }

  protected abstract void setUp(View view);

  @Override
  public void onDestroy() {
    if (mUnBinder != null) {
      mUnBinder.unbind();
    }
    super.onDestroy();
  }

  public interface Callback {

    void onFragmentAttached();

    void onFragmentDetached(String tag);
  }
}
