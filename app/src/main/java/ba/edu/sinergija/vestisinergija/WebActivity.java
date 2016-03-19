package ba.edu.sinergija.vestisinergija;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Created by Android on 19.3.2016.
 */
public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // postavljamo view za ovaj activity
        setContentView(R.layout.activity_web);
        // ukljucujemo dugme back u action bar-u
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // postavljamo naziv u action bar-u
        getSupportActionBar().setTitle(getString(R.string.webview));

        // instanciramo intent
        Intent intent = getIntent();
        // uzimanje podatka iz intent-a
        String url = intent.getStringExtra("url");

        WebView webView = (WebView) findViewById(R.id.webview);
        // postavljanje naseg WebViewClient-a
        webView.setWebViewClient(new NewsClient());
        // ucitavanje stranice
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // u slucaju da je korisnik pritisnuo dugme back
        if (item.getItemId() == android.R.id.home) {
            // unisti ovaj activity
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
