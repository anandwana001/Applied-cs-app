package anandwana001.com.acs.ui.setupaccount;

import anandwana001.com.acs.R;
import anandwana001.com.acs.base.BaseActivity;
import anandwana001.com.acs.ui.main.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 12:07 PM.
 */

public class SetupAccountActivity extends BaseActivity implements SetupMvpView {

  @BindView(R.id.student_name)
  TextInputEditText studentName;
  @BindView(R.id.college_name)
  TextInputEditText collegeName;
  @BindView(R.id.submit)
  Button submit;

  @Inject
  SetupMvpPresenter<SetupMvpView> mPresenter;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, SetupAccountActivity.class);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setup);

    getActivityComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));
    mPresenter.onAttach(SetupAccountActivity.this);
  }

  @OnClick(R.id.submit)
  void submitToFirebase(){
    mPresenter.setupAccountOnFirebase(studentName.getText().toString(), collegeName.getText().toString());
  }

  @Override
  public void openMainActivityFromSetup() {
    Intent intent = MainActivity.getStartIntent(SetupAccountActivity.this);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }

  @Override
  protected void onDestroy() {
    mPresenter.onDetach();
    super.onDestroy();
  }

  @Override
  protected void setUp() {

  }

  @Override
  public void onFragmentAttached() {

  }

  @Override
  public void onFragmentDetached(String tag) {

  }
}
