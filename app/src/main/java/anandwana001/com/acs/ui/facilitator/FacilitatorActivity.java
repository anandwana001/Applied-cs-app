package anandwana001.com.acs.ui.facilitator;

import anandwana001.com.acs.R;
import anandwana001.com.acs.ui.adapter.FacilitatorStudentViewHolder;
import anandwana001.com.acs.ui.model.FacilitatorStudentModel;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by
 * dell on
 * 15-01-2018 at
 * 04:40 PM.
 */

public class FacilitatorActivity extends AppCompatActivity {

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.recycler_view_project)
  RecyclerView attendeList;

  private String KEY_LAYOUT_MANAGER = "list_state";
  private static final int SPAN_COUNT = 2;

  private enum LayoutManagerType {
    GRID_LAYOUT_MANAGER,
    LINEAR_LAYOUT_MANAGER
  }

  protected LayoutManagerType mCurrentLayoutManagerType;
  protected RecyclerView.LayoutManager mLayoutManager;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_facilitator);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    mLayoutManager = new LinearLayoutManager(this);
    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

    if (savedInstanceState != null) {
      mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
    }
    setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

    if (getIntent().getStringExtra("workshop_id") != null) {
      String workshop_id = getIntent().getStringExtra("workshop_id");

      FirebaseRecyclerAdapter<FacilitatorStudentModel, FacilitatorStudentViewHolder> eventEventViewHolderFirebaseRecyclerAdapter
          = new FirebaseRecyclerAdapter<FacilitatorStudentModel, FacilitatorStudentViewHolder>(
          FacilitatorStudentModel.class,
          R.layout.content_facilitator_workshop,
          FacilitatorStudentViewHolder.class,
          FirebaseDatabase.getInstance().getReference().child("attende_list")
              .child(workshop_id)
      ) {
        @Override
        protected void populateViewHolder(FacilitatorStudentViewHolder viewHolder, final FacilitatorStudentModel model, final int position) {
          viewHolder.setFacilitatorStudentName(model.getName());
        }
      };
      attendeList.setAdapter(eventEventViewHolderFirebaseRecyclerAdapter);
    }
  }

  public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
    int scrollPosition = 0;

    if (attendeList.getLayoutManager() != null) {
      scrollPosition = ((LinearLayoutManager) attendeList.getLayoutManager())
          .findFirstCompletelyVisibleItemPosition();
    }
    switch (layoutManagerType) {
      case GRID_LAYOUT_MANAGER:
        mLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        break;
      case LINEAR_LAYOUT_MANAGER:
        mLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        break;
      default:
        mLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }
    attendeList.setLayoutManager(mLayoutManager);
    attendeList.scrollToPosition(scrollPosition);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      // Respond to the action bar's Up/Home button
      case android.R.id.home:
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
          // This activity is NOT part of this app's task, so create a new task
          // when navigating up, with a synthesized back stack.
          TaskStackBuilder.create(this)
              // Add all of this activity's parents to the back stack
              .addNextIntentWithParentStack(upIntent)
              // Navigate up to the closest parent
              .startActivities();
        } else {
          // This activity is part of this app's task, so simply
          // navigate up to the logical parent activity.
          NavUtils.navigateUpTo(this, upIntent);
        }
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
