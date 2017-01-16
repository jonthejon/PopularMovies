package jonathanoliveira.org.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by JonathanOliveira on 12/01/17.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.PosterViewHolder> {

    private Movie[] moviesArr;
    final private GridItemClickListener myInterface;
    private Picasso picasso;


    public GridAdapter(GridItemClickListener myInterface, Picasso picasso) {
        this.myInterface = myInterface;
        this.picasso = picasso;
    }

    public void setMoviesArr(Movie[] moviesArr) {
        this.moviesArr = null;
        this.moviesArr = moviesArr;
        notifyDataSetChanged();
    }

    public Movie getMovie(int position) {
        return moviesArr[position];
    }

    public interface GridItemClickListener {
        void OnClickView(int position);
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
        if (moviesArr == null) {
            return 0;
        }
        return moviesArr.length;
    }


    class PosterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

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
//            String movieName = this.poster.getContentDescription().toString();
            myInterface.OnClickView(position);
        }
    }

}
