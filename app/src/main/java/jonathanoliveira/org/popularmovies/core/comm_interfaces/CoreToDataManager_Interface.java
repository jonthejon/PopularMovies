package jonathanoliveira.org.popularmovies.core.comm_interfaces;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public interface CoreToDataManager_Interface {

    void getDataFromDataManager(String callType);

    String getManagerAPICallName();

    String getManagerUtilsCallName();

}
