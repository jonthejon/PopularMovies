package jonathanoliveira.org.popularmovies.data.manager;

import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToDataManager_Interface;
import jonathanoliveira.org.popularmovies.data.api.APIHandler;
import jonathanoliveira.org.popularmovies.data.api.APIHandler_Interface;
import jonathanoliveira.org.popularmovies.data.utilities.UtilitiesHandler;
import jonathanoliveira.org.popularmovies.data.utilities.UtilitiesHandler_Interface;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public class Manager implements Manager_Interface, CoreToDataManager_Interface {

    private static final CoreToDataManager_Interface commInstance = new Manager();
    private static final Manager_Interface manager = new Manager();

    private APIHandler_Interface apiHandlerInterface;

    private final String API_CALLNAME = "api";
    private final String UTILS_CALLNAME = "utils";

    public static CoreToDataManager_Interface getCommManagerInstance() {
        return commInstance;
    }

    public static Manager_Interface getManagerInstance() {
        return manager;
    }

    private Manager() {
    }

    @Override
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
    }

    @Override
    public void bindDataToView(String moviePosterPath) {
        apiHandlerInterface.bindPicassoToView(Core.getCoreInstance().getPresenterInstance(), moviePosterPath);
    }

    @Override
    public void dataAPICallback(String rawJSONResult) {
        Core.getCoreInstance().returnData(getObjectFromJSON(rawJSONResult));
    }

    @Override
    public void errorAPICallback() {
        Core.getCoreInstance().returnDataError();
    }


    @Override
    public Movie[] getObjectFromJSON(String rawJSONResult) {
        UtilitiesHandler_Interface utils = new UtilitiesHandler();
        return utils.convertJSONtoData(rawJSONResult);
    }

    @Override
    public String getManagerAPICallName() {
        return this.API_CALLNAME;
    }

    @Override
    public String getManagerUtilsCallName() {
        return this.UTILS_CALLNAME;
    }
}
