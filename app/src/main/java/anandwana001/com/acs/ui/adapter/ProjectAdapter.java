package anandwana001.com.acs.ui.adapter;

import anandwana001.com.acs.R;
import anandwana001.com.acs.ui.adapter.ProjectAdapter.MyViewHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;

/**
 * Created by
 * dell on
 * 13-01-2018 at
 * 05:58 AM.
 */

public class ProjectAdapter extends Adapter<MyViewHolder> {

  Context context;
  String[] projectName;
  String[] projectSub;
  TypedArray projectImage;

  public ProjectAdapter(Context context, String[] projectName, String[] projectSub, TypedArray projectImage) {
    this.context = context;
    this.projectName = projectName;
    this.projectSub = projectSub;
    this.projectImage = projectImage;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.content_project, parent, false);

    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.projectName.setText(projectName[position].toUpperCase());
    holder.projectSub.setText(projectSub[position]);
    Glide.with(context).load(projectImage.getDrawable(position)).into(holder.thumbnail);
  }

  @Override
  public int getItemCount() {
    return projectName.length;
  }

  public class MyViewHolder extends ViewHolder {

    @BindView(R.id.thumbnail)
    ImageView thumbnail;
    @BindView(R.id.project_name)
    TextView projectName;
    @BindView(R.id.project_sub)
    TextView projectSub;

    public MyViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
