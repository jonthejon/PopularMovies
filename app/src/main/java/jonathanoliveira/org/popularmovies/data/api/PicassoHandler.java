package jonathanoliveira.org.popularmovies.data.api;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jonathanoliveira.org.popularmovies.R;

/**
 * Created by JonathanOliveira on 10/03/17.
 */

public class PicassoHandler {

        void  bindPicassoToView(Context context, String stringURL, ImageView view) {
        Picasso.with(context)
                .load(stringURL)
                .placeholder(R.drawable.ic_wb_cloudy_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(view);
    }
}
