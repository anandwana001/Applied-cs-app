package anandwana001.com.acs.ui.fragment.project;

import anandwana001.com.acs.R;
import anandwana001.com.acs.ui.adapter.ProjectAdapter;
import anandwana001.com.acs.ui.fragment.student.StudentFragment;
import anandwana001.com.acs.ui.main.MainActivity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by
 * dell on
 * 13-01-2018 at
 * 05:52 AM.
 */

public class ProjectFragment extends Fragment {

  @BindView(R.id.recycler_view_project)
  RecyclerView recyclerViewProject;
  Unbinder unbind;

  private Parcelable mListState;
  private GridLayoutManager mLayoutManager;
  private String LIST_STATE_KEY = "list_state";
  private ProjectAdapter projectAdapter;

  public static ProjectFragment newInstance() {
    Bundle args = new Bundle();
    ProjectFragment fragment = new ProjectFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
    unbind = ButterKnife.bind(this, rootView);

    projectAdapter = new ProjectAdapter(getContext(), this.getResources().getStringArray(R.array.project_titles_array), this.getResources().getStringArray(R.array.project_titles_sub), this.getResources().obtainTypedArray(R.array.project_image_array));
    mLayoutManager = new GridLayoutManager(getContext(), 2);
    recyclerViewProject.setLayoutManager(mLayoutManager);
    recyclerViewProject.setAdapter(projectAdapter);

    return rootView;
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mListState = mLayoutManager.onSaveInstanceState();
    outState.putParcelable(LIST_STATE_KEY, mListState);
  }

  @Override
  public void onResume() {
    super.onResume();
    if (mListState != null) {
      mLayoutManager.onRestoreInstanceState(mListState);
    }
    getActivity().setTitle(MainActivity.TAG_PROJ);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbind.unbind();
  }
}
