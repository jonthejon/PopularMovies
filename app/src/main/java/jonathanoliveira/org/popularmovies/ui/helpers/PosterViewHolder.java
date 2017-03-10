package jonathanoliveira.org.popularmovies.ui.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;

/**
 * Created by JonathanOliveira on 10/03/17.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder implements OnClickListener, CoreToPresenter_Interface {

    private GridAdapter_Interface adapter;
    private ImageView poster;

    public PosterViewHolder(View itemView, GridAdapter_Interface adapter) {
        super(itemView);
        this.adapter = adapter;
        poster = (ImageView) itemView.findViewById(R.id.poster_id);
        itemView.setOnClickListener(this);
    }

    void bindData(String movieName, String completePosterPath) {
        Core_Interface core = Core.getCoreInstance();
        core.registerCoreToPresenterInterface(this);
        core.bindData(completePosterPath);
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
}
