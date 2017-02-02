package net.innovationyard.www.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.innovationyard.www.R;

/**
 * Created by Aakash on 11/10/2016.
 */
public class InsecEvents extends Activity {
    private WebView webView;
    private ProgressBar progressBar;
    private LinearLayout layoutProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insec_events);
        webView = (WebView) findViewById(R.id.webViewevents);
        progressBar = (ProgressBar) findViewById(R.id.progressBarevents);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        layoutProgress = (LinearLayout) findViewById(R.id.layoutProgressevents);
        webView.setVisibility(View.GONE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        //settings.setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {
           /* @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().endsWith("google.com")) {
                    // This is my web site, so do not override; let my WebView load the page
                    return false;
                }
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }
*/
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                layoutProgress.setVisibility(View.GONE);
                progressBar.setIndeterminate(false);
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('col-lg-4 col-sm-4')[0].style.display='none'; })()");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                layoutProgress.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(true);
                super.onPageStarted(view, url, favicon);


            }
        });
        if(isOnline()) {
            webView.loadUrl("http://insecnotification.azurewebsites.net/events.php");
        } else {
            String summary = "<html><body><font color='red'>No Internet Connection</font></body></html>";
            webView.loadData(summary, "text/html", null);
            toast("No Internet Connection.");
        }
    }
    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }

    }
    private class MyAppWeViewClient extends WebViewClient{


    }
}

