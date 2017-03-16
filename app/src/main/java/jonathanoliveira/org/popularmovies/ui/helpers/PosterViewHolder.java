package jonathanoliveira.org.popularmovies.ui.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToViewHolder_Interface;

/**
 * Created by JonathanOliveira on 10/03/17.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder implements OnClickListener, CoreToViewHolder_Interface {

    private GridAdapter_Interface adapter;
    private ImageView poster;
    private String poster_path;

    public PosterViewHolder(View itemView, GridAdapter_Interface adapter) {
        super(itemView);
        this.adapter = adapter;
        poster = (ImageView) itemView.findViewById(R.id.poster_id);
        itemView.setOnClickListener(this);
    }

    void bindData(String movieName, String completePosterPath) {
        this.poster_path = completePosterPath;
        Core_Interface core = Core.getCoreInstance();
        core.registerCoreToViewHolder_Interface(this);
        core.bindData(core.getViewHolderChannel());
        poster.setContentDescription(movieName);
    }

    @Override
    public void onClick(View v) {
        int position = this.getAdapterPosition();
        adapter.onAdapterClickCallback(position);
    }

    @Override
    public Context getContext() {
        return adapter.getContext();
    }

    @Override
    public ImageView getImageView() {
        return poster;
    }

    @Override
    public String getString() {
        return this.poster_path;
    }
}
