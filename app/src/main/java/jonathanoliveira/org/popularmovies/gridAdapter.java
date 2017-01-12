package jonathanoliveira.org.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JonathanOliveira on 12/01/17.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.PosterViewHolder> {

//    List<String> posterUrlArr = new ArrayList<>();
    private Context context;
    private int sizeDisplay;

    public GridAdapter(Context context, int sizeDisplay) {
        this.context = context;
        this.sizeDisplay = sizeDisplay;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.poster_view_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem,parent,shouldAttachToParentImmediately);
        PosterViewHolder posterViewHolder = new PosterViewHolder(view);
        return posterViewHolder;
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        String movieName = holder.poster.getContentDescription().toString();
        int resourceId = R.drawable.ic_cloud_off_black_24dp;
        holder.bindData(resourceId,movieName);
    }

    @Override
    public int getItemCount() {
//        return posterUrlArr.size();
        return sizeDisplay;
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;

        public PosterViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster_id);
        }

        void bindData(int resourceId, String movieName) {
            poster.setImageResource(resourceId);
            poster.setContentDescription(movieName);
        }

    }

}
