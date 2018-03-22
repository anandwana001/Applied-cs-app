package anandwana001.com.acs.ui.fragment.about;

import anandwana001.com.acs.base.BasePresenter;
import javax.inject.Inject;

/**
 * Created by
 * anandwana001 on
 * 22-03-2018 at
 * 09:26 AM.
 */

public class AboutPresenter<V extends AboutMvpView> extends BasePresenter<V>
    implements AboutMvpPresenter<V> {

  @Inject
  public AboutPresenter(){
  }
}
