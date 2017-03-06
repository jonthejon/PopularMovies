package jonathanoliveira.org.popularmovies.comm_interfaces;

import android.content.Intent;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface CallerIntent_Presenter extends GetObject_Presenter {

    void operateOnCallerIntent(Intent callerIntent);

}
