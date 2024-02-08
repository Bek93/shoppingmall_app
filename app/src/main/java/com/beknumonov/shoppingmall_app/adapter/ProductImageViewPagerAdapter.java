package com.beknumonov.shoppingmall_app.adapter;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.beknumonov.shoppingmall_app.fragments.BannerFragment;
import com.beknumonov.shoppingmall_app.fragments.ImageFragment;
import com.beknumonov.shoppingmall_app.model.Banner;
import com.beknumonov.shoppingmall_app.model.ProductImage;

import java.util.ArrayList;

public class ProductImageViewPagerAdapter extends FragmentStateAdapter {

    private ArrayList<ProductImage> productImageArrayList;

    public ProductImageViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ProductImage> productImageArrayList) {
        super(fragmentActivity);
        this.productImageArrayList = productImageArrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ProductImage productImage = productImageArrayList.get(position);
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("imageUrl", productImage.getImage());
        imageFragment.setArguments(bundle);

        return imageFragment;
    }

    @Override
    public int getItemCount() {
        return productImageArrayList.size();
    }
}
