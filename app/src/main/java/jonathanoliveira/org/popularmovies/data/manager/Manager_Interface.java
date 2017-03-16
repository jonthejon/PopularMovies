package jonathanoliveira.org.popularmovies.data.manager;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public interface Manager_Interface {

//    void dataAPICallback(String rawJSONResult);
    void dataAPICallback(String rawJSONResult, int loader_id);

    void errorAPICallback();

}
