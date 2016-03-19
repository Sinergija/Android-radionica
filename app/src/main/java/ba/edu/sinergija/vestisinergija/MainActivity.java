package ba.edu.sinergija.vestisinergija;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    /* onCreate metod je metod koji se prvi pokrece pri startovanju Activity-a. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // postavljanje view-a za ovaj activity
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });

        try {
            List<News> newsList = new ArrayList<>();
            // parsiramo JSON array
            JSONArray jsonArray = new JSONArray(NewConstants.JSON_NEWS);

            int size = jsonArray.length();
            for (int i = 0; i < size; i++) {
                // parsiramo objekte u array JSON-u.
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                // dodajemo objekte u listu
                newsList.add(new News(jsonObject.getString("naslov")
                                , jsonObject.getString("url")));
            }
            // definisemo ListView
            ListView listView = (ListView) findViewById(R.id.list);
            // Instanciramo nas ArrayAdapter
            NewsAdapter newsAdapter = new NewsAdapter(getApplicationContext(), newsList);

            // Dodeljujemo ListView adapter
            listView.setAdapter(newsAdapter);

            // Kreiramo listener koji osluskuje koji item u listi je izabran od strane korisnika
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Uzimamo objekat iz liste na poziciji koja je izabrana
                    News news = (News) parent.getItemAtPosition(position);
                    // Instanciramo Intent
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    // Skaldistimo podatak u intent
                    intent.putExtra("url", news.url);
                    // otvaramo novi activitys
                    startActivity(intent);
                }
            });

        } catch (Throwable e) {
            e.getStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
