package jonathanoliveira.org.popularmovies.comm_interfaces;

import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.GetSetObjectStateBoolean;
import jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks.InflateMenu;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieGrid_Activity_Interface extends InflateMenu, GetSetObjectStateBoolean {

    void loadMoviesData(boolean sortByPopularity);
}
