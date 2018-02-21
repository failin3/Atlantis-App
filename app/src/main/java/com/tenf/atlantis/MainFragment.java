package com.tenf.atlantis;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class MainFragment extends Fragment {
    private static String url = "https://pot-atlantis.000webhostapp.com";
    WebView WebView;
    View view;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        WebView = (WebView) view.findViewById(R.id.webView);
        WebSettings webSettings = WebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        WebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
        WebView.loadUrl(url);
        return view;
    }

    public void goBack() {
        if (WebView.canGoBack()) {
            WebView.goBack();
        }
    }
    public void refresh() {
        progressBar.setVisibility(View.VISIBLE);
        WebView.setVisibility(View.GONE);
        WebView.loadUrl(WebView.getUrl());
        WebView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                WebView.setVisibility(View.VISIBLE);
            }
        });
    }
}
