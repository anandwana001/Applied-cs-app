package anandwana001.com.acs.utils;

import anandwana001.com.acs.AcsApplication;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 09:17 PM.
 */

public class CommonUtils {

  public static int RC_SIGN_IN = 1;
  private static final String FACILITATOR_LOGIN = "preferences_fac_login";

  public static final String TAG_PROJ = "Projects";
  public static final String TAG_LEARN = "Learn";
  public static final String TAG_FAC = "Facilitator";
  public static final String TAG_STU = "Student";
  public static final String TAG_ABOT = "About";

  private CommonUtils() {
  }

  public static ProgressDialog showLoadingDialog(Context context, String message) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message);
    progressDialog.show();
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    return progressDialog;
  }

  private static SharedPreferences getPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(AcsApplication.getInstance()
        .getApplicationContext());
  }

  public static boolean getBoolean(String preferenceKey, boolean preferenceDefaultValue) {
    return getPreferences().getBoolean(preferenceKey, preferenceDefaultValue);
  }

  public static void putBoolean(String preferenceKey, boolean preferenceValue) {
    getPreferences().edit().putBoolean(preferenceKey, preferenceValue).apply();
  }
}
