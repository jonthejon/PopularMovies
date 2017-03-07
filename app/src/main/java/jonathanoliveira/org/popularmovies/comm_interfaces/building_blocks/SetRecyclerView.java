package jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks;

import android.support.v7.widget.RecyclerView;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface SetRecyclerView {

     RecyclerView.Adapter setAdapter(boolean hasLayoutManager);

     void bindAdapter(RecyclerView.Adapter adapter);

     boolean setLayoutManager();

}
