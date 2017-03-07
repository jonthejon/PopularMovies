package jonathanoliveira.org.popularmovies.comm_interfaces;

import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.AdapterClickHandlingCallback;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.CreateIntent;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.HandleMenuItemClick;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.LoadAsyncTask;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.UpdateRecyclerViewAdapter;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.WriteToast;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieGrid_Presenter_Interface extends HandleMenuItemClick, UpdateRecyclerViewAdapter, WriteToast,
        LoadAsyncTask, AdapterClickHandlingCallback, CreateIntent {
}
