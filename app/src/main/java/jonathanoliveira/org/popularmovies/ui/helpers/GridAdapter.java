package jonathanoliveira.org.popularmovies.ui.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.data.api.APIUtils;
import jonathanoliveira.org.popularmovies.ui.presenters.MovieGrid_Presenter_Interface;

/**
 * Created by JonathanOliveira on 12/01/17.
 */

// REFERENCES TO ADAPTER MUST BE OF GRIDADAPTER TYPE INSTEAD OF INTERFACE TYPE IN ORDER FOR ADAPTER TO PROPERLY WORK
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.PosterViewHolder> implements GridAdapter_Interface {

    private Movie[] moviesArr;
    final private MovieGrid_Presenter_Interface presenter;
    private Picasso picasso;


    public GridAdapter(MovieGrid_Presenter_Interface presenter, Context context) {
        this.presenter = presenter;
        picasso = Picasso.with(context);
    }

    @Override
    public void notifyAdapterForDataChange(Movie[] moviesArr) {
        this.moviesArr = null;
        this.moviesArr = moviesArr;
        notifyDataSetChanged();
    }

    @Override
    public Movie getAdapterDataSingleItem(int position) {
        return moviesArr[position];
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.poster_view_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem,parent,false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        String movieName = moviesArr[position].getMovie_title();
        String completePosterPath = APIUtils.build_Picasso_Url(moviesArr[position].getPoster_path());
        holder.bindData(movieName, completePosterPath);
    }

    @Override
    public int getItemCount() {
        if (moviesArr == null) {
            return 0;
        }
        return moviesArr.length;
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        ImageView poster;

        public PosterViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster_id);
            itemView.setOnClickListener(this);
        }

        void bindData(String movieName, String completePosterPath) {
            picasso.load(completePosterPath)
                    .placeholder(R.drawable.ic_wb_cloudy_black_24dp)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .into(poster);
            poster.setContentDescription(movieName);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            presenter.onAdapterClickCallback(position);
        }
    }

}
