package jonathanoliveira.org.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

// TODO: 12/01/17 (1) create a menu button for the settings with a toast to check
// TODO: 12/01/17 (1.1) create a new xml menu resource in a new menu resource folder
// TODO: 12/01/17 (1.2) create a new item called settings in the menu xml with proper properties such as order, if room, etc
// TODO: 12/01/17 (1.3) override the proper methods to create the menu and implement them
// TODO: 12/01/17 (1.4) on the itemclicked method, create a Toast to check if button is working
// TODO: 12/01/17 (2) create new detailsActivity and link it as a child to the main activity
// TODO: 12/01/17 (3) create new Layout that will hold all images from the server API in Grid
// TODO: 12/01/17 (4) create Adapter that will give the Grid all the views that it needs
// TODO: 12/01/17 (5) create a grid view using the RecyclerView and chain it with the Adapter
// TODO: 12/01/17 (6) install Picasso framework to help you with image handling
// TODO: 12/01/17 (7) connect to the movies API and receive data, populating the views in the Adapter
// TODO: 12/01/17 (8) create click listeners in each grid cell of the GridView and link to the detailsActivity
// TODO: 12/01/17 (9) update data info to parcelable so it can take phone tilt without crashing
// TODO: 12/01/17 (10) populate details activity with custom data of the clicked movie
// TODO: 12/01/17 (11) create different layout for tilted or not tilted phone
// TODO: 12/01/17 (12) give functionality to the settings button to change the order of the sorting