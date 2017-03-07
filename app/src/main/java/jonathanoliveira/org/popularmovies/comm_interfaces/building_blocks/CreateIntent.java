package jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks;

import android.content.Context;
import android.content.Intent;

import jonathanoliveira.org.popularmovies.Movie;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface CreateIntent {

    Intent createIntent(Context context, Movie extraObject);

}
