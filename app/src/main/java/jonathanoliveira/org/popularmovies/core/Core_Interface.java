package jonathanoliveira.org.popularmovies.core;

import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToViewHolder_Interface;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface Core_Interface {

    void registerCoreToAdapterInterface(CoreToAdapter_Interface coreToAdapterInterface);

    void registerCoreToPresenterInterface(CoreToPresenter_Interface coreToPresenterInterface);

    void registerCoreToViewHolder_Interface(CoreToViewHolder_Interface coreToViewHolderInterface);

    void returnData(Movie[] movieArr, int channel);
    void returnData(String rawJSONResult, int channel);

    void returnDataError();

    void setBooleanOption(boolean option);

    boolean getBooleanOption();
    int getAdapterChannel();
    int getViewHolderChannel();
    int getPresenterChannel();
    int getPresenterTrailersChannel();
    int getPresenterReviewsChannel();

//    void getData(String callType);
    void getData(int channel);

    void bindData(String moviePosterPath);
    void bindData(int channel);

    String getManagerAPICallName();

    CoreToPresenter_Interface getPresenterInstance();

    CoreToAdapter_Interface getAdapterPresenterInstance();

    CoreToViewHolder_Interface getViewHolderInstance();

}
