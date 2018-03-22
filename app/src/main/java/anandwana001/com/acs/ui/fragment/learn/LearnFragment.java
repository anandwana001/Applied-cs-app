package anandwana001.com.acs.ui.fragment.learn;

import static anandwana001.com.acs.utils.CommonUtils.TAG_LEARN;

import anandwana001.com.acs.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by
 * dell on
 * 13-01-2018 at
 * 09:01 AM.
 */

public class LearnFragment extends Fragment {

  public static LearnFragment newInstance() {
    Bundle args = new Bundle();
    LearnFragment fragment = new LearnFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_learn,container,false);
    return rootView;
  }

  @Override
  public void onResume() {
    super.onResume();
    getActivity().setTitle(TAG_LEARN);
  }
}
