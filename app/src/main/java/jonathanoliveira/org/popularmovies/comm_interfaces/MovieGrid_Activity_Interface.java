package jonathanoliveira.org.popularmovies.comm_interfaces;

import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.GetContext;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.GetPresenter;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.GetRecyclerViewAdapter;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.GetSetObjectStateBoolean;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.InflateMenu;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.LoadAsyncTask;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieGrid_Activity_Interface extends InflateMenu, GetSetObjectStateBoolean,
        LoadAsyncTask, GetPresenter, GetRecyclerViewAdapter, GetContext {
}
