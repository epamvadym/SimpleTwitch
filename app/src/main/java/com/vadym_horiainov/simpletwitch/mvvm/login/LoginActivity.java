package com.vadym_horiainov.simpletwitch.mvvm.login;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vadym_horiainov.simpletwitch.BR;
import com.vadym_horiainov.simpletwitch.R;
import com.vadym_horiainov.simpletwitch.databinding.ActivityLoginBinding;
import com.vadym_horiainov.simpletwitch.di.annotations.OauthUrl;
import com.vadym_horiainov.simpletwitch.mvvm.base.BindingActivity;
import com.vadym_horiainov.simpletwitch.mvvm.user.UserInfoActivity;

import javax.inject.Inject;

public class LoginActivity extends BindingActivity<ActivityLoginBinding, LoginActivityVM> {

    @Inject
    @OauthUrl
    String oauthUrl;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getBinding();
        setUp();
        subscribeToLiveData();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUp() {
        WebView webView = binding.webView;
        webView.clearCache(true);
        webView.getSettings().setSaveFormData(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("access_token")) {
                    getViewModel().accessTokenReceived(url);

                    CookieManager cm = CookieManager.getInstance();
                    cm.removeAllCookie();

                    view.clearCache(true);
                    view.clearHistory();
                    view.clearFormData();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(oauthUrl);
    }

    private void subscribeToLiveData() {
        getViewModel().getUserInfoReceivedLiveData().observe(this, success -> {
            if (success != null && success) finish();;
        });
    }

    private void startUserActivity() {
        startActivity(new Intent(this, UserInfoActivity.class));
        finish();
    }

    @Override
    public LoginActivityVM createViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(LoginActivityVM.class);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}
