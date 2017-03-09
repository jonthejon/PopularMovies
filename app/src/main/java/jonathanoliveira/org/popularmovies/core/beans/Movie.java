package jonathanoliveira.org.popularmovies.core.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JonathanOliveira on 13/01/17.
 */

public class Movie implements Parcelable {

    private String poster_path;
    private String movie_title;
    private String overview;
    private String release_date;
    private double vote_average;

    public Movie() {
        this("Title not available","Poster not available","Overview not available","Release date not available",0.0);
    }

    public Movie(String poster_path) {
        this("Title not available",poster_path,"Overview not available","Release date not available",0.0);
    }

    public Movie(String poster_path, String movie_title) {
        this(movie_title,poster_path,"Overview not available","Release date not available",0.0);
    }

    public Movie(String poster_path, String movie_title, String overview) {
        this(movie_title,poster_path,overview,"Release date not available",0.0);
    }

    protected Movie(Parcel source) {
        this.poster_path = source.readString();
        this.movie_title = source.readString();
        this.overview = source.readString();
        this.release_date = source.readString();
        this.vote_average = source.readDouble();
    }

    public Movie(String poster_path, String movie_title, String overview, String release_date, double vote_average) {
        this.poster_path = poster_path;
        this.movie_title = movie_title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
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
