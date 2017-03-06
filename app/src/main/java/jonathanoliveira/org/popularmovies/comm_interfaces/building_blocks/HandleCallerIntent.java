package jonathanoliveira.org.popularmovies.comm_interfaces.building_blocks;

import android.content.Intent;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface HandleCallerIntent extends GetObject {

    void operateOnCallerIntent(Intent callerIntent);

}
