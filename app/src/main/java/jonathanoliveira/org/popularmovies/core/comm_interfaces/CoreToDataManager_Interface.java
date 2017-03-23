package jonathanoliveira.org.popularmovies.core.comm_interfaces;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public interface CoreToDataManager_Interface {

//    void getDataFromDataManager(String callType);
    void getDataFromDataManager(int channel);

    String getManagerAPICallName();

    String getManagerUtilsCallName();

    void bindDataToView(String moviePosterPath);
    void bindDataToView(int channel);

    void workOnContentProvider(int channel);

    Movie[] getMovieArr();

    Movie[] getObjectArrFromJSON(String rawJSONResult);
    String[][] getTrailerArrFromJSON(String rawJSONResult);
    String[][] getReviewArrFromJSON(String rawJSONResult);

}
