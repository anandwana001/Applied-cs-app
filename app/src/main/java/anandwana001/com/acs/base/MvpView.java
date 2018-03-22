package anandwana001.com.acs.base;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 08:54 PM.
 */

public interface MvpView {

  void showMessage(String message);
  void showLoading(String message);
  void hideLoading();
  void onError(String errorMessage);
}
