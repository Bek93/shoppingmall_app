package com.beknumonov.shoppingmall_app.activity;

import android.view.LayoutInflater;

import com.beknumonov.shoppingmall_app.base.BaseActivity;
import com.beknumonov.shoppingmall_app.databinding.ActivityOrderDetailsBinding;

/*
    1. Get Order data from intent ( getIntent().getSerializableExtra().
    2. Add order details (textview) to Order Details Page xml.
    3. Print Order details on textview. Order number, Order status etc
    4. Add RecyclerView
        1. Create List Adapter for ordered product list.
        2. Create ViewHolder for ordered products
        3. Set LayoutManger
        4. Set Adapter


*/

public class OrderDetailsActivity extends BaseActivity<ActivityOrderDetailsBinding> {
    @Override
    protected ActivityOrderDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityOrderDetailsBinding.inflate(inflater);
    }
}
