package ba.edu.sinergija.vestisinergija;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Android on 19.3.2016.
 */
public class NewsClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
