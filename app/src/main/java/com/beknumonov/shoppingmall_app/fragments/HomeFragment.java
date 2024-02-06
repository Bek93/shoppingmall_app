package com.beknumonov.shoppingmall_app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.beknumonov.shoppingmall_app.adapter.BannerViewPagerAdapter;
import com.beknumonov.shoppingmall_app.adapter.IndicatorAdapter;
import com.beknumonov.shoppingmall_app.adapter.ProductListAdapter;
import com.beknumonov.shoppingmall_app.base.BaseFragment;
import com.beknumonov.shoppingmall_app.databinding.FragmentHomeBinding;
import com.beknumonov.shoppingmall_app.model.Banner;
import com.beknumonov.shoppingmall_app.model.Product;
import com.beknumonov.shoppingmall_app.model.ProductList;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private ArrayList<Banner> bannerArrayList;
    private ArrayList<Product> productArrayList;

    private BannerViewPagerAdapter bannerViewPagerAdapter;

    private IndicatorAdapter indicatorAdapter;
    private ProductListAdapter productListAdapter;

    @Override
    protected FragmentHomeBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentHomeBinding.inflate(inflater, parent, toAttach);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerArrayList = new ArrayList<>();
        productArrayList = new ArrayList<>();
        bannerViewPagerAdapter = new BannerViewPagerAdapter(getActivity(), bannerArrayList);
        indicatorAdapter = new IndicatorAdapter(bannerArrayList);
        productListAdapter = new ProductListAdapter(productArrayList);

        loadBanners();
        loadPopularProductList();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.bannerViewPager.setAdapter(bannerViewPagerAdapter);

        binding.pagerIndicatorView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.pagerIndicatorView.setAdapter(indicatorAdapter);


        binding.popularProductRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.popularProductRecyclerView.setAdapter(productListAdapter);


        binding.bannerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                indicatorAdapter.setSelectedDotPosition(position);
                indicatorAdapter.notifyDataSetChanged();
            }
        });

    }

    private void loadBanners() {

        bannerArrayList.clear();
        Call<ArrayList<Banner>> call = parent.mainApi.getBanners();

        call.enqueue(new Callback<ArrayList<Banner>>() {
            @Override
            public void onResponse(Call<ArrayList<Banner>> call, Response<ArrayList<Banner>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    bannerArrayList.addAll(response.body());
                    bannerViewPagerAdapter.notifyDataSetChanged();
                    indicatorAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Banner>> call, Throwable t) {

            }
        });
    }

    private void loadPopularProductList() {

        Call<ArrayList<Product>> call = parent.mainApi.getPopularProducts();

        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    productArrayList.addAll(response.body());
                    productListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
}
