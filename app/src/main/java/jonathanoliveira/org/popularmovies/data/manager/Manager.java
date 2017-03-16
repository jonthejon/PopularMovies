package jonathanoliveira.org.popularmovies.data.manager;

import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToDataManager_Interface;
import jonathanoliveira.org.popularmovies.data.api.APIHandler;
import jonathanoliveira.org.popularmovies.data.api.APIHandler_Interface;
import jonathanoliveira.org.popularmovies.data.api.APIUtils;
import jonathanoliveira.org.popularmovies.data.utilities.UtilitiesHandler;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public class Manager implements Manager_Interface, CoreToDataManager_Interface {

    private static final CoreToDataManager_Interface commInstance = new Manager();
    private static final Manager_Interface manager = new Manager();

    private APIHandler_Interface apiHandlerInterface;

    private final String API_CALLNAME = "api";
    private final String UTILS_CALLNAME = "utils";

    private Movie[] movieArrDataHolder;

    public static CoreToDataManager_Interface getCommManagerInstance() {
        return commInstance;
    }

    public static Manager_Interface getManagerInstance() {
        return manager;
    }

    private Manager() {
    }

/*    @Override
    public void getDataFromDataManager(String callType) {
        switch (callType) {

            case API_CALLNAME:
                if (apiHandlerInterface == null) {
                    apiHandlerInterface = new APIHandler();
                }
//                apiHandlerInterface.startAsyncTask(Core.getCoreInstance().getBooleanOption());
                Core_Interface core = Core.getCoreInstance();
                apiHandlerInterface.startAsyncTaskLoader(core.getAdapterPresenterInstance(), core.getBooleanOption());
                break;

            case UTILS_CALLNAME:
                break;
        }
    }*/

    @Override
    public void getDataFromDataManager(int channel) {

        if (apiHandlerInterface == null) {
            apiHandlerInterface = new APIHandler();
        }
        apiHandlerInterface.startAsyncTaskLoader(channel);
    }

    @Override
    public void bindDataToView(String moviePosterPath) {
        apiHandlerInterface.bindPicassoToView(Core.getCoreInstance().getPresenterInstance(), moviePosterPath);
    }

    @Override
    public void bindDataToView(int channel) {
        Core_Interface core = Core.getCoreInstance();
        switch (channel) {
            case 31:
                apiHandlerInterface.bindPicassoToView(core.getViewHolderInstance(), core.getViewHolderInstance().getString());
                break;
            case 37:
                apiHandlerInterface.bindPicassoToView(core.getPresenterInstance(), core.getPresenterInstance().getString());
                break;
        }
    }

/*    @Override
    public void dataAPICallback(String rawJSONResult) {
        int channel = Core.getCoreInstance().getAdapterChannel();
        Core.getCoreInstance().returnData(getObjectFromJSON(rawJSONResult), channel);
    }*/

    @Override
    public void dataAPICallback(String rawJSONResult, int loader_id) {

        Core_Interface core = Core.getCoreInstance();

        switch (loader_id) {
            case 22:
//                movieArrDataHolder = getObjectArrFromJSON(rawJSONResult);
//                int channel = Core.getCoreInstance().getAdapterChannel();
//                Core.getCoreInstance().returnData(channel);
                core.returnData(rawJSONResult, core.getAdapterChannel());
                break;

            case 23:
                core.returnData(rawJSONResult, core.getPresenterTrailersChannel());
                break;

            case 24:
                core.returnData(rawJSONResult, core.getPresenterReviewsChannel());
                break;
        }
    }

    @Override
    public void errorAPICallback() {
        Core.getCoreInstance().returnDataError();
    }


    @Override
    public Movie[] getObjectArrFromJSON(String rawJSONResult) {
//        UtilitiesHandler_Interface utils = new UtilitiesHandler();
//        return utils.convertJSONtoMoviesArr(rawJSONResult);
        return new UtilitiesHandler().convertJSONtoMoviesArr(rawJSONResult);
    }

    @Override
    public String[][] getTrailerArrFromJSON(String rawJSONResult) {
        String[][] allTrailers = new UtilitiesHandler().convertJSONtoTrailerArr(rawJSONResult);
        for (String[] trailerData : allTrailers) {
            trailerData[1] = APIUtils.build_Youtube_Url(trailerData[1]);
        }
        return allTrailers;
    }

    @Override
    public String[][] getReviewArrFromJSON(String rawJSONResult) {
//        UtilitiesHandler_Interface utils = new UtilitiesHandler();
//        return utils.convertJSONtoReviewArr(rawJSONResult);
        return new UtilitiesHandler().convertJSONtoReviewArr(rawJSONResult);
    }

    @Override
    public String getManagerAPICallName() {
        return this.API_CALLNAME;
    }

    @Override
    public String getManagerUtilsCallName() {
        return this.UTILS_CALLNAME;
    }

    @Override
    public Movie[] getMovieArr() {
        return movieArrDataHolder;
    }
}
