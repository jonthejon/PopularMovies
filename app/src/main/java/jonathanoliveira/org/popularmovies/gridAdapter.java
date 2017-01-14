package jonathanoliveira.org.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JonathanOliveira on 12/01/17.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.PosterViewHolder> {

    private Movie[] moviesArr;
    private Context context;
    private int sizeDisplay;

    public GridAdapter(Context context) {
        this.context = context;
    }

    public GridAdapter(Context context, Movie[] moviesArr) {
        this.context = context;
        this.sizeDisplay = sizeDisplay;
        this.moviesArr = moviesArr;
    }

    public void setMoviesArr(Movie[] moviesArr) {
        this.moviesArr = moviesArr;
        notifyDataSetChanged();
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
        String movieName = moviesArr[position].getMovie_title();
        String completePosterPath = NetworkUtils.build_Picasso_Url(moviesArr[position].getPoster_path());
        holder.bindData(movieName, completePosterPath);
    }

    @Override
    public int getItemCount() {
        if (moviesArr == null || moviesArr.length == 0) {
            return 0;
        }
        return moviesArr.length;
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;

        public PosterViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster_id);
        }

        void bindData(String movieName, String completePosterPath) {
            Picasso.with(context)
                    .load(completePosterPath)
                    .placeholder(R.drawable.ic_wb_cloudy_black_24dp)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(poster);
            poster.setContentDescription(movieName);
        }

    }

}
