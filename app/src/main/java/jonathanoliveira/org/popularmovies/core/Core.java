package jonathanoliveira.org.popularmovies.core;

import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToDataManager_Interface;
import jonathanoliveira.org.popularmovies.data.manager.Manager;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public class Core implements Core_Interface {

    private static final Core_Interface core = new Core();

    private CoreToAdapter_Interface coreToAdapterInterface;
    private CoreToDataManager_Interface dataManager;
    private boolean searchOption;

    private Core() {
        this.dataManager = Manager.getCommManagerInstance();
    }

    public static Core_Interface getCoreInstance() {
        return core;
    }

    @Override
    public void registerCoreToAdapterInterface(CoreToAdapter_Interface coreToAdapterInterface) {
        this.coreToAdapterInterface = coreToAdapterInterface;
    }

    @Override
    public void getData(String callType) {
        dataManager.getDataFromDataManager(callType);
    }

    @Override
    public void returnData(Movie[] movieArr) {
        coreToAdapterInterface.updateAdapter(movieArr);
    }

    @Override
    public void returnDataError() {
        coreToAdapterInterface.writeToast();
    }

    @Override
    public void setBooleanOption(boolean option) {
        this.searchOption = option;
    }

    @Override
    public boolean getBooleanOption() {
        return this.searchOption;
    }

    @Override
    public final String getManagerAPICallName() {
        return dataManager.getManagerAPICallName();
    }
}
