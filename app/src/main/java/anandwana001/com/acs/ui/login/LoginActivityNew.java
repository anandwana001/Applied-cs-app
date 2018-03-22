package anandwana001.com.acs.ui.login;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.R;
import anandwana001.com.acs.base.BaseActivity;
import anandwana001.com.acs.ui.main.MainActivity;
import anandwana001.com.acs.ui.setupaccount.SetupAccountActivity;
import anandwana001.com.acs.utils.CommonUtils;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 09:22 PM.
 */

public class LoginActivityNew extends BaseActivity implements LoginMvpView {

  @Inject
  LoginMvpPresenter<LoginMvpView> mPresenter;

  @BindView(R.id.ib_google_login)
  ImageButton ibGoogleLogin;

  private GoogleApiClient googleApiClient;

  public static Intent getStartIntent(Context context) {
    Intent intent = new Intent(context, LoginActivityNew.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    getActivityComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));
    mPresenter.onAttach(LoginActivityNew.this);
  }


  @OnClick(R.id.ib_google_login)
  void onGoogleLoginClick(View v) {
    GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(
        R.string.default_web_client_id))
        .requestEmail()
        .build();
    googleApiClient = new GoogleApiClient.Builder(this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
        .build();
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
    startActivityForResult(signInIntent, CommonUtils.RC_SIGN_IN);
    showLoading("Signing in...");
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CommonUtils.RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

      if (result.isSuccess()) {
        GoogleSignInAccount account = result.getSignInAccount();
        firebaseAuthentication(account);
      } else {
        hideLoading();
        showMessage("Google Sign-In failed.");
      }
    }
  }

  public void firebaseAuthentication(final GoogleSignInAccount googleSignInAccount) {
    AuthCredential credential = GoogleAuthProvider
        .getCredential(googleSignInAccount.getIdToken(), null);
    AcsApplication.getInstanceAuth().signInWithCredential(credential).addOnCompleteListener(
        this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {

            if (!task.isSuccessful()) {
              hideLoading();
              showMessage("Authentication failed.");
            } else {
              mPresenter.checkUserExist();
            }
          }
        });
  }


  @Override
  public void openMainActivity() {
    Intent intent = MainActivity.getStartIntent(LoginActivityNew.this);
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
  public void openSetupAccountActivity() {
    Intent intent = SetupAccountActivity.getStartIntent(LoginActivityNew.this);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }

  @Override
  public void onFragmentAttached() {

  }

  @Override
  public void onFragmentDetached(String tag) {

  }
}
