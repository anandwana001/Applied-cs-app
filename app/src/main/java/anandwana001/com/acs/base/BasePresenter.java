package anandwana001.com.acs.base;

import com.google.firebase.auth.FirebaseAuth;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 09-01-2018 at
 * 09:00 PM.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

  private V mMvpView;

  @Inject
  public BasePresenter() {
  }

  @Override
  public void onAttach(V mvpView) {
    mMvpView = mvpView;
  }

  @Override
  public void onDetach() {
    mMvpView = null;
  }

  @Override
  public void setUserAsLoggedOut() {
    FirebaseAuth.getInstance().signOut();
  }

  protected V getMvpView() {
    return mMvpView;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) throw new MvpViewNotAttachedException();
  }

  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.onAttach(MvpView) before" +
          " requesting data to the Presenter");
    }
  }

  protected boolean isViewAttached() {
    return mMvpView != null;
  }
}
