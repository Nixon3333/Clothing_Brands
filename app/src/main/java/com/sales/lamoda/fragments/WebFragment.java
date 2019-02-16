package com.sales.lamoda.fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;
import com.sales.lamoda.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;


public class WebFragment extends Fragment {

    public static WebView mWebView;
    public static ProgressBar progressBar;
    private int count = 0;
    private String myUrl;
    public ImageView websplash;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setRetainInstance(true);
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        mWebView = (WebView) v.findViewById(R.id.mWebView);
        progressBar = v.findViewById(R.id.progressBar);
        websplash = v.findViewById(R.id.websplash);


        progressBar.setVisibility(View.VISIBLE);


        mWebView.setWebViewClient(new MyWebViewClient());


        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);


        mWebView.loadUrl(getResources().getString(R.string.url));
        //mWebView.loadUrl(getResources().getString(R.string.test_url));

        return v;
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("file:///android_asset/NoInternet.html");
        }


        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
            progressBar.setVisibility(View.INVISIBLE);
            //mWebView.loadUrl("javascript:(function() {document.getElementsByClassName('b-nav__item b-nav__item_logo')[0].innerHTML = 'My new text!';})()");
            //mWebView.loadUrl("javascript:(function() {document.getElementsByClassName('user-unauthorized')[0].style.display = 'none';})()");
            /* mWebView.loadUrl("javascript:(function() {document.getElementsByClassName('user-unauthorized')[0].style.display = '';})()");*/
            //mWebView.loadUrl("javascript:(function() {document.getElementsByClassName('ibanner1968719 smartbanner')[0].style.display = 'none';})()");
            mWebView.loadUrl("javascript:(function() {document.getElementsByClassName('b-nav__item b-nav__item_logo')[0].style.display = 'none';})()");
            mWebView.loadUrl("javascript:(function() {document.getElementsByClassName('smartbanner__close')[0].click();})()");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    websplash.setVisibility(View.INVISIBLE);
                }
            }, 1000);
            CookieSyncManager.getInstance().sync();
            //websplash.setVisibility(View.INVISIBLE);

            Log.d("count", String.valueOf(count));
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            websplash.setVisibility(View.VISIBLE);
        }
    }
}

