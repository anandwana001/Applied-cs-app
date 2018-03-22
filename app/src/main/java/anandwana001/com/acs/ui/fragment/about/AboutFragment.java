package anandwana001.com.acs.ui.fragment.about;

import static anandwana001.com.acs.utils.CommonUtils.TAG_ABOT;

import anandwana001.com.acs.R;
import anandwana001.com.acs.base.BaseFragment;
import anandwana001.com.acs.di.component.ActivityComponent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import javax.inject.Inject;

/**
 * Created by
 * dell on
 * 13-01-2018 at
 * 09:00 AM.
 */

public class AboutFragment extends BaseFragment implements AboutMvpView {

  @Inject
  AboutMvpPresenter<AboutMvpView> mPresenter;

  public static AboutFragment newInstance() {
    Bundle args = new Bundle();
    AboutFragment fragment = new AboutFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_about,container,false);

    ActivityComponent component = getActivityComponent();
    if (component != null) {
      component.inject(this);
      setUnBinder(ButterKnife.bind(this, rootView));
      mPresenter.onAttach(this);
    }

    return rootView;
  }
  @Override
  public void onResume() {
    super.onResume();
    getActivity().setTitle(TAG_ABOT);
  }
  @Override
  protected void setUp(View view) {
  }
  @Override
  public void onDestroyView() {
    mPresenter.onDetach();
    super.onDestroyView();
  }
}