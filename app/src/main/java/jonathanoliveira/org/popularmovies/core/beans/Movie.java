package jonathanoliveira.org.popularmovies.core.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JonathanOliveira on 13/01/17.
 */

public class Movie implements Parcelable {

    private final String poster_path;
    private final String movie_title;
    private final String overview;
    private final String release_date;
    private final double vote_average;
    private final int movie_id;
    private String[][] trailers;
    private String[][] reviews;


/*    public Movie() {
        this("Poster not available","Title not available","Overview not available","Release date not available",0.0,0);
    }

    public Movie(String poster_path) {
        this(poster_path,"Title not available","Overview not available","Release date not available",0.0,0);
    }

    public Movie(String poster_path, String movie_title) {
        this(poster_path,movie_title,"Overview not available","Release date not available",0.0,0);
    }

    public Movie(String poster_path, String movie_title, int movie_id) {
        this(poster_path,movie_title,"Overview not available","Release date not available",0.0,0);
    }

    public Movie(String poster_path, String movie_title, String overview, String release_date, double vote_average) {
        this(poster_path,movie_title,overview,"Release date not available",0.0,0);
    }

    public Movie(String poster_path, String movie_title, String overview, String release_date, double vote_average, int movie_id) {
        this.poster_path = poster_path;
        this.movie_title = movie_title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.movie_id = movie_id;
    }*/

    private Movie(MovieBuilder builder) {
        this.poster_path = builder.poster_path;
        this.movie_title = builder.movie_title;
        this.overview = builder.overview;
        this.release_date = builder.release_date;
        this.vote_average = builder.vote_average;
        this.movie_id = builder.movie_id;
    }

    public static class MovieBuilder {

        private String poster_path;
        private String movie_title;
        private String overview;
        private String release_date;
        private double vote_average;
        private final int movie_id;

        public MovieBuilder(int movie_id) {
            this.movie_id = movie_id;
        }
        public MovieBuilder poster_path(String poster_path) {
            this.poster_path = poster_path;
            return this;
        }
        public MovieBuilder movie_title(String movie_title) {
            this.movie_title = movie_title;
            return this;
        }
        public MovieBuilder overview(String overview) {
            this.overview = overview;
            return this;
        }
        public MovieBuilder release_date(String release_date) {
            this.release_date = release_date;
            return this;
        }
        public MovieBuilder vote_average(double vote_average) {
            this.vote_average = vote_average;
            return this;
        }
        public Movie build() {
            String noDataString = "No data available";
            if (this.poster_path == null) this.poster_path = noDataString;
            if (this.movie_title == null) this.movie_title = noDataString;
            if (this.overview == null) this.overview = noDataString;
            if (this.release_date == null) this.release_date = noDataString;
            return new Movie(this);
        }
    }

    public int getMovie_id() {
        return movie_id;
    }

/*
    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }
*/

    public String getPoster_path() {
        return poster_path;
    }

/*    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }*/

    public String getMovie_title() {
        return movie_title;
    }

/*    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }*/

    public String getOverview() {
        return overview;
    }

/*    public void setOverview(String overview) {
        this.overview = overview;
    }*/

    public String getRelease_date() {
        return release_date;
    }

/*    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }*/

    public double getVote_average() {
        return vote_average;
    }

/*    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }*/

    public String[][] getTrailers() {
        return trailers;
    }

    public void setTrailers(String[][] trailers) {
        this.trailers = trailers;
    }

    public String[][] getReviews() {
        return reviews;
    }

    public void setReviews(String[][] reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "poster_path='" + poster_path + '\'' +
                ", movie_title='" + movie_title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }

    protected Movie(Parcel source) {
        this.poster_path = source.readString();
        this.movie_title = source.readString();
        this.overview = source.readString();
        this.release_date = source.readString();
        this.vote_average = source.readDouble();
        this.movie_id = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(movie_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeDouble(vote_average);
        dest.writeInt(movie_id);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };
}
