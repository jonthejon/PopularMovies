# Popular Movies

Video playlist Android app that allows the user to visualize the latest or most popular movie trailers and save them as favorites.

## Using the app

The default main screen shows the most popular movies (displayed in order of popularity) as in the movies list of the MovieDB database. By clicking in the options menu, you can see the list of the top-rated movies of the MoviesDB or your list of saved favorite movies.

![Main Screen](https://github.com/jonthejon/PopularMovies/blob/master/main_screen.png)
![Main Screen - options menu](https://github.com/jonthejon/PopularMovies/blob/master/main_screen_options.png)

By clicking in any poster, you're taken to the details page of that particular movie.
This page contains:
  1) number of trailers available for seeing in the app
  2) number of user reviews available in the app for you to read
  3) short outline of the movie
  4) favorite button
      by clicking this button, you'll add this movie to your favorites list so you can always have access to it later
  5) list of available trailers
      by clicking any one of this, you'll be taken to the YouTube app of your phone for visualizing the trailer
  6) list of available reviews 
  
![Details Screen](https://github.com/jonthejon/PopularMovies/blob/master/movie_details_1.png)
![Details Screen - scroll](https://github.com/jonthejon/PopularMovies/blob/master/movie_details_2.png)  

### Installing

This app is not yet deployed in the Google App Store. This means that in order for you to use it, you must clone this repository and install the app in your phone using an USB cable.
You'll need the Android Studio IDE installed in your computer.

[ANDROID STUDIO](https://developer.android.com/studio/index.html) - download the latest Android Studio, in case you don't have it.

## Libraries

[PICASSO](http://square.github.io/picasso/) - A powerful image downloading and caching library for Android

[OK HTTP](http://square.github.io/okhttp/) - An HTTP & HTTP/2 client for Android and Java applications

## Acknowledgments

* This app architecture was loosely based on Uncle Bob's Clean Architecture, an approach for software multi-tier architecture. [This](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) is a good explanation of this approach.
* This app was a course project for the [Udacity Android Developer Nanodegree](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801).
