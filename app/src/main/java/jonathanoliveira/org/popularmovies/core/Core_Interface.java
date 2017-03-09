package jonathanoliveira.org.popularmovies.core;

import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface Core_Interface {

    void registerCoreToAdapterInterface(CoreToAdapter_Interface coreToAdapterInterface);

    void returnData(Movie[] movieArr);

    void returnDataError();

    void setBooleanOption(boolean option);

    boolean getBooleanOption();

    void getData(String callType);

    String getManagerAPICallName();

}
