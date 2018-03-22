package anandwana001.com.acs.ui.main;

import anandwana001.com.acs.base.BasePresenter;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 06-02-2018 at
 * 07:56 PM.
 */

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V>
    implements MainMvpPresenter<V> {

  @Inject
  public MainPresenter(){

  }
}
