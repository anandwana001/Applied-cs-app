package anandwana001.com.acs.ui.fragment.facilitator;

import static anandwana001.com.acs.utils.CommonUtils.TAG_FAC;

import anandwana001.com.acs.R;
import anandwana001.com.acs.ui.fragment.facilitator.name.FacilitatorNameFragment;
import anandwana001.com.acs.ui.fragment.facilitator.workshop.FacilitatorFragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * dell on
 * 30-01-2018 at
 * 07:39 AM.
 */

public class FacilitatorFragmentNew extends Fragment {

  @BindView(R.id.tab_layout)
  TabLayout tabLayout;
  @BindView(R.id.feed_view_pager)
  ViewPager feedViewPager;
  Unbinder unbinder;

  public static FacilitatorFragmentNew newInstance() {
    Bundle args = new Bundle();
    FacilitatorFragmentNew fragment = new FacilitatorFragmentNew();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main_facilitator, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    setupViewPager(feedViewPager);

    tabLayout.setupWithViewPager(feedViewPager);

    return rootView;
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
    adapter.addFragment(new FacilitatorNameFragment(), "Facilitator");
    adapter.addFragment(new FacilitatorFragment(), "Workshops");
    viewPager.setAdapter(adapter);
  }

  class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }
  @Override
  public void onResume() {
    super.onResume();
    getActivity().setTitle(TAG_FAC);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
