package jonathanoliveira.org.popularmovies;

import java.io.Serializable;

/**
 * Created by JonathanOliveira on 13/01/17.
 */

public class Movie implements Serializable {

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
}
