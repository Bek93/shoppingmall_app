package com.beknumonov.shoppingmall_app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.beknumonov.shoppingmall_app.adapter.IndicatorAdapter;
import com.beknumonov.shoppingmall_app.adapter.ProductImageViewPagerAdapter;
import com.beknumonov.shoppingmall_app.base.BaseActivity;
import com.beknumonov.shoppingmall_app.base.RequestCallback;
import com.beknumonov.shoppingmall_app.databinding.ActivityProductDetailsBinding;
import com.beknumonov.shoppingmall_app.databinding.ActivityProductListBinding;
import com.beknumonov.shoppingmall_app.model.Product;
import com.beknumonov.shoppingmall_app.model.ProductImage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity<ActivityProductDetailsBinding> {

    private Product product;
    private ProductImageViewPagerAdapter viewPagerAdapter;

    private IndicatorAdapter indicatorAdapter;
    private ArrayList<ProductImage> productImageArrayList = new ArrayList<>();

    @Override
    protected ActivityProductDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityProductDetailsBinding.inflate(inflater);
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            setTitle(product.getTitle());
            loadProductDetails();
        } else {
            finish();
        }

    }

    private void buildProductData() {
        productImageArrayList.addAll(product.getImages());
        viewPagerAdapter = new ProductImageViewPagerAdapter(ProductDetailsActivity.this, productImageArrayList);
        binding.productImageViewPager.setAdapter(viewPagerAdapter);
        indicatorAdapter = new IndicatorAdapter(productImageArrayList.size());
        binding.pagerIndicatorView.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        binding.pagerIndicatorView.setAdapter(indicatorAdapter);
        binding.productImageViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                indicatorAdapter.setSelectedDotPosition(position);
                indicatorAdapter.notifyDataSetChanged();
            }
        });


        binding.productBrand.setText(product.getBrand());
        binding.productName.setText(product.getTitle());
        binding.productPriceCurrent.setText(product.getPriceCurrent());
        binding.productPriceOriginal.setText(product.getPriceOriginal());
        binding.productDetails.setText(product.getDescription());

    }

    private void loadProductDetails() {
        Call<Product> call = mainApi.getProductDetails(product.getId());

        call.enqueue(new RequestCallback<Product>() {
            @Override
            protected void onResponseSuccess(Call<Product> call, Response<Product> response) {
                product = response.body();
                buildProductData();
            }

            @Override
            protected void onResponseFailed(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Request is failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
