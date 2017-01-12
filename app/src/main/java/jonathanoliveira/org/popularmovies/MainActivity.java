package jonathanoliveira.org.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridAdapter = new GridAdapter(this,20);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_grid);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager gridManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setAdapter(mGridAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.settings_button:
                Toast.makeText(this,"Settings button clicked.", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}