package jonathanoliveira.org.popularmovies.data.api;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JonathanOliveira on 10/03/17.
 */

public class InternetConnection {

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response okResponse = null;
        String response = null;

        try {
            okResponse = client.newCall(request).execute();
        } finally {
            if (okResponse != null) {
                response = okResponse.body().string();
            }
        }

        return response;

/*        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }*/
    }

}
