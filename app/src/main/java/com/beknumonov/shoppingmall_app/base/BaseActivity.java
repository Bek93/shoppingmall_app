package com.beknumonov.shoppingmall_app.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.beknumonov.shoppingmall_app.R;
import com.beknumonov.shoppingmall_app.activity.ProductDetailsActivity;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {

    protected T binding;

    protected abstract T inflateViewBinding(LayoutInflater inflater);

    public MainApi mainApi;

    private Toolbar toolbar;
    private TextView tvPageTitle;

    public PreferenceManager preferenceManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Xar bir activity uchun T toifadagi ViewBinding qaytaradi. Eslatib o'tamiz generate qilingan hamma ViewBindingslar ViewBinding dan extended qilingan
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());
        mainApi = ApiService.provideApi(MainApi.class, this);
        preferenceManager = PreferenceManager.getInstance(this);
        toolbar = binding.getRoot().findViewById(R.id.toolbar);
        tvPageTitle = binding.getRoot().findViewById(R.id.pageTitle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (hasBackButton()) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }

        }
    }


    protected boolean hasBackButton() {
        return false;
    }

    protected void setTitle(String title) {

        if (tvPageTitle != null) {
            tvPageTitle.setText(title);
        }
    }


    public void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }


}
