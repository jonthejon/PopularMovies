package jonathanoliveira.org.popularmovies.core;

import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToDataManager_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToViewHolder_Interface;
import jonathanoliveira.org.popularmovies.data.manager.Manager;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public class Core implements Core_Interface {

    private static final Core_Interface core = new Core();

    private CoreToAdapter_Interface coreToAdapterInterface;
    private CoreToPresenter_Interface coreToPresenterInterface;
    private CoreToViewHolder_Interface coreToViewHolderInterface;
    private CoreToDataManager_Interface dataManager;
    private boolean searchOption;

//    private final int ADAPTER_CHANNEL = 29;
    private final int ADAPTER_MOVIES_CHANNEL = 29;
    private final int VIEWHOLDER_CHANNEL = 31;
    private final int PRESENTER_CHANNEL = 37;
    private final int PRESENTER_TRAILERS_CHANNEL = 41;
    private final int PRESENTER_REVIEWS_CHANNEL = 43;

    private Core() {
        this.dataManager = Manager.getCommManagerInstance();
    }

    public static Core_Interface getCoreInstance() {
        return core;
    }

    @Override
    public void registerCoreToAdapterInterface(CoreToAdapter_Interface coreToAdapterInterface) {
        if (this.coreToAdapterInterface != null) {
            this.coreToAdapterInterface = null;
        }
        this.coreToAdapterInterface = coreToAdapterInterface;
    }

    @Override
    public void registerCoreToPresenterInterface(CoreToPresenter_Interface coreToPresenterInterface) {
        if (this.coreToPresenterInterface != null) {
            this.coreToPresenterInterface = null;
        }
        this.coreToPresenterInterface = coreToPresenterInterface;
    }

    @Override
    public void registerCoreToViewHolder_Interface(CoreToViewHolder_Interface coreToViewHolderInterface) {
        if (this.coreToViewHolderInterface != null) {
            this.coreToViewHolderInterface = null;
        }
        this.coreToViewHolderInterface = coreToViewHolderInterface;
    }

/*    @Override
    public void getData(String callType) {
        dataManager.getDataFromDataManager(callType);
    }*/

    @Override
    public void getData(int channel) {
        dataManager.getDataFromDataManager(channel);
    }

    @Override
    public void bindData(String moviePosterPath) {
        dataManager.bindDataToView(moviePosterPath);
    }

    @Override
    public void bindData(int channel) {
        dataManager.bindDataToView(channel);
    }

    @Override
    public void returnData(Movie[] movieArr, int channel) {
        switch (channel) {
/*            case ADAPTER_CHANNEL:
                coreToAdapterInterface.updateAdapter(movieArr);
                break;*/
            case ADAPTER_MOVIES_CHANNEL:
                coreToAdapterInterface.updateAdapter(movieArr);
                break;
        }
    }

    @Override
    public void returnData(String rawJSONResult, int channel) {
        switch (channel) {
            case ADAPTER_MOVIES_CHANNEL:
                coreToAdapterInterface.updateAdapter(dataManager.getObjectArrFromJSON(rawJSONResult));
                break;
            case PRESENTER_TRAILERS_CHANNEL:
                coreToPresenterInterface.bindTrailerstoViews(dataManager.getTrailerArrFromJSON(rawJSONResult));
                break;
/*            case PRESENTER_REVIEWS_CHANNEL:
                coreToPresenterInterface.bindReviewstoViews(dataManager.getReviewArrFromJSON(rawJSONResult));
                break;*/
        }
    }

    @Override
    public void returnDataError() {
        coreToAdapterInterface.writeError();
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
    public int getAdapterChannel() {
//        return ADAPTER_CHANNEL;
        return ADAPTER_MOVIES_CHANNEL;
    }

    @Override
    public int getViewHolderChannel() {
        return VIEWHOLDER_CHANNEL;
    }

    @Override
    public int getPresenterChannel() {
        return PRESENTER_CHANNEL;
    }

    @Override
    public int getPresenterTrailersChannel() {
        return PRESENTER_TRAILERS_CHANNEL;
    }

    @Override
    public int getPresenterReviewsChannel() {
        return PRESENTER_REVIEWS_CHANNEL;
    }

    @Override
    public final String getManagerAPICallName() {
        return dataManager.getManagerAPICallName();
    }

    @Override
    public CoreToPresenter_Interface getPresenterInstance() {
        return coreToPresenterInterface;
    }

    @Override
    public CoreToAdapter_Interface getAdapterPresenterInstance() {
        return coreToAdapterInterface;
    }

    @Override
    public CoreToViewHolder_Interface getViewHolderInstance() {
        return coreToViewHolderInterface;
    }
}
