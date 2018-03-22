package anandwana001.com.acs.ui.main;

import anandwana001.com.acs.AcsApplication;
import anandwana001.com.acs.R;
import anandwana001.com.acs.base.BaseActivity;
import anandwana001.com.acs.ui.fragment.about.AboutFragment;
import anandwana001.com.acs.ui.fragment.facilitator.FacilitatorFragmentNew;
import anandwana001.com.acs.ui.fragment.learn.LearnFragment;
import anandwana001.com.acs.ui.fragment.project.ProjectFragment;
import anandwana001.com.acs.ui.fragment.student.StudentFragment;
import anandwana001.com.acs.ui.login.LoginActivityNew;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 01:06 PM.
 */

public class MainActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener,
              MainMvpView {

  @Inject
  MainMvpPresenter<MainMvpView> mPresenter;

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.nav_view)
  NavigationView navView;
  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;

  public static final String TAG_PROJ = "Projects";
  public static final String TAG_LEARN = "Learn";
  public static final String TAG_FAC = "Facilitator";
  public static final String TAG_STU = "Student";
  public static final String TAG_ABOT = "About";
  public static String CURRENT_TAG = TAG_PROJ;

  public static int navItemIndex = 0;
  public Fragment fragment;

  private FirebaseAuth mFirebaseAuth;
  private FirebaseUser mFirebaseUser;
  private AuthStateListener mAuthListener;
  private GoogleApiClient googleApiClient;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mFirebaseAuth = AcsApplication.getInstanceAuth();
    mFirebaseUser = mFirebaseAuth.getCurrentUser();
    mAuthListener = new AuthStateListener() {
      @Override
      public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
        if (mFirebaseUser == null) {
          Intent loginIntent = new Intent(MainActivity.this, LoginActivityNew.class);
          loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(loginIntent);
          finish();
        }
      }
    };

    setContentView(R.layout.activity_main);
    getActivityComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));
    mPresenter.onAttach(MainActivity.this);
    setSupportActionBar(toolbar);

    GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(
        R.string.default_web_client_id))
        .requestEmail()
        .build();
    googleApiClient = new GoogleApiClient.Builder(this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
        .build();

    if (savedInstanceState != null) {
      fragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
          android.R.anim.fade_out);
      fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
      fragmentTransaction.commit();
    } else {
      navItemIndex = 0;
      CURRENT_TAG = TAG_PROJ;
      loadHomeFragment();
    }

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
    navView.setNavigationItemSelectedListener(this);
  }

  private void loadHomeFragment() {
    selectNavMenu();

    if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
      drawer.closeDrawers();
    } else {
      fragment = getHomeFragment();
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
          android.R.anim.fade_out);
      fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
      fragmentTransaction.commit();
      drawer.closeDrawers();
    }
  }

  private Fragment getHomeFragment() {
    Fragment sendFragment;
    switch (navItemIndex) {
      case 0:
        sendFragment = new ProjectFragment();
        break;
      case 1:
        sendFragment = new LearnFragment();
        break;
      case 2:
        sendFragment = new FacilitatorFragmentNew();
        break;
      case 3:
        sendFragment = new StudentFragment();
        break;
      case 4:
        sendFragment = new AboutFragment();
        break;
      default:
        sendFragment = new ProjectFragment();
    }
    return sendFragment;
  }

  private void selectNavMenu() {
    navView.getMenu().getItem(navItemIndex).setChecked(true);
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
      return;
    }
    super.onBackPressed();
  }

  @Override
  public void onStart() {
    super.onStart();
    googleApiClient.connect();
    mFirebaseAuth.addAuthStateListener(mAuthListener);
  }

  @Override
  public void onStop() {
    if (mAuthListener != null) {
      mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }
    googleApiClient.disconnect();
    super.onStop();
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
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    switch (menuItem.getItemId()) {
      case R.id.nav_pro:
        navItemIndex = 0;
        CURRENT_TAG = TAG_PROJ;
        break;
      case R.id.nav_learn:
        navItemIndex = 1;
        CURRENT_TAG = TAG_LEARN;
        break;
      case R.id.nav_fac:
        navItemIndex = 2;
        CURRENT_TAG = TAG_FAC;
        break;
      case R.id.nav_stu:
        navItemIndex = 3;
        CURRENT_TAG = TAG_STU;
        break;
      case R.id.nav_about:
        navItemIndex = 4;
        CURRENT_TAG = TAG_ABOT;
        break;
      case R.id.nav_logot:
        navItemIndex = 5;
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivityNew.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        break;
      default:
        navItemIndex = 0;
    }
    if (menuItem.isChecked()) {
      menuItem.setChecked(false);
    } else {
      menuItem.setChecked(true);
    }
    menuItem.setChecked(true);

    loadHomeFragment();
    return true;
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getSupportFragmentManager().putFragment(outState, "myFragmentName", fragment);
  }

  @Override
  public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onRestoreInstanceState(savedInstanceState, persistentState);
  }

  @Override
  public void onFragmentAttached() {

  }

  @Override
  public void onFragmentDetached(String tag) {

  }
}