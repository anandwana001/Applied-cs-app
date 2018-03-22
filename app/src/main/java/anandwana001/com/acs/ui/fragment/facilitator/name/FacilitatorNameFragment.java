package anandwana001.com.acs.ui.fragment.facilitator.name;

import anandwana001.com.acs.R;
import anandwana001.com.acs.ui.adapter.FacilitatorNameViewHolder;
import anandwana001.com.acs.ui.model.FacilitatorNameModel;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by
 * dell on
 * 30-01-2018 at
 * 08:00 AM.
 */

public class FacilitatorNameFragment extends Fragment {

  @BindView(R.id.recycler_view_project)
  RecyclerView facilitatorNameRecyclerView;
  Unbinder unbinder;

  private static final int SPAN_COUNT = 2;
  private String KEY_LAYOUT_MANAGER = "list_state";

  private enum LayoutManagerType {
    GRID_LAYOUT_MANAGER,
    LINEAR_LAYOUT_MANAGER
  }

  protected FacilitatorNameFragment.LayoutManagerType mCurrentLayoutManagerType;
  protected LayoutManager mLayoutManager;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
    unbinder = ButterKnife.bind(this, rootView);

    mLayoutManager = new LinearLayoutManager(getContext());
    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

    if (savedInstanceState != null) {
      mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(
          KEY_LAYOUT_MANAGER);
    }
    setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

    FirebaseRecyclerAdapter<FacilitatorNameModel,
        FacilitatorNameViewHolder> eventEventViewHolderFirebaseRecyclerAdapter
        = new FirebaseRecyclerAdapter<FacilitatorNameModel, FacilitatorNameViewHolder>(
        FacilitatorNameModel.class,
        R.layout.content_facilitator_workshop,
        FacilitatorNameViewHolder.class,
        FirebaseDatabase.getInstance().getReference().child("Facilitator_name")
    ) {
      @Override
      protected void populateViewHolder(FacilitatorNameViewHolder viewHolder,
          final FacilitatorNameModel model, final int position) {
        viewHolder.setFacilitatorName(model.getName());
      }
    };

    facilitatorNameRecyclerView.setAdapter(eventEventViewHolderFirebaseRecyclerAdapter);
    return rootView;
  }


  public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
    int scrollPosition = 0;

    if (facilitatorNameRecyclerView.getLayoutManager() != null) {
      scrollPosition = ((LinearLayoutManager) facilitatorNameRecyclerView.getLayoutManager())
          .findFirstCompletelyVisibleItemPosition();
    }
    switch (layoutManagerType) {
      case GRID_LAYOUT_MANAGER:
        mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        break;
      case LINEAR_LAYOUT_MANAGER:
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        break;
      default:
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }
    facilitatorNameRecyclerView.setLayoutManager(mLayoutManager);
    facilitatorNameRecyclerView.scrollToPosition(scrollPosition);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
    super.onSaveInstanceState(savedInstanceState);
  }
}
