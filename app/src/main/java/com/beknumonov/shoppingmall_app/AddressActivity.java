package com.beknumonov.shoppingmall_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.webkit.JavascriptInterface;

import androidx.annotation.Nullable;

import com.beknumonov.shoppingmall_app.base.BaseActivity;
import com.beknumonov.shoppingmall_app.databinding.ActivityAddressBinding;


public class AddressActivity extends BaseActivity<ActivityAddressBinding> {
    @Override
    protected ActivityAddressBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddressBinding.inflate(inflater);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webView.loadUrl("http://address.annyong.store/");
        binding.webView.addJavascriptInterface(new WebAppInterface(), "CallbackWebInterface");
    }


    class WebAppInterface {

        public WebAppInterface() {
        }

        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        public void getAddress(String postcode, String address) {
            Intent intent = getIntent();
            intent.putExtra("postcode", postcode);
            intent.putExtra("address", address);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
