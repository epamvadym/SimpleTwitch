package com.vadym_horiainov.simpletwitch.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vadym_horiainov.simpletwitch.R;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        WebView webView = findViewById(R.id.web);
        webView.clearCache(true);
        webView.getSettings().setSaveFormData(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("access_token")) {
                    String mAccessToken = getAccessTokenFromURL(url);
                    Intent intent = new Intent(BrowserActivity.this, UserActivity.class);
                    intent.putExtra("1", mAccessToken);
                    startActivity(intent);

                    CookieManager cm = CookieManager.getInstance();
                    cm.removeAllCookie();

                    view.clearCache(true);
                    view.clearHistory();
                    view.clearFormData();
                    finish();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl("https://id.twitch.tv/oauth2/authorize?response_type=token&client_id=0s4cg0hmn8rq4rrv4ex8rtkexoape7&redirect_uri=http://localhost&scope=viewing_activity_read&state=c3ab8aa609ea11e793ae92361f002671");
    }

    private String getAccessTokenFromURL(String url) {
        String startIdentifier = "access_token";
        String endIdentifier = "&scope";

        int startIndex = url.indexOf(startIdentifier) + startIdentifier.length() + 1;
        int lastIndex = url.indexOf(endIdentifier);

        return url.substring(startIndex, lastIndex);
    }

}
