package com.beknumonov.shoppingmall_app.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beknumonov.shoppingmall_app.adapter.OrderedProductListAdapter;
import com.beknumonov.shoppingmall_app.base.BaseActivity;
import com.beknumonov.shoppingmall_app.base.RequestCallback;
import com.beknumonov.shoppingmall_app.databinding.ActivityOrderDetailsBinding;
import com.beknumonov.shoppingmall_app.model.Order;
import com.beknumonov.shoppingmall_app.model.OrderedProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

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

    private OrderedProductListAdapter adapter;
    private ArrayList<OrderedProduct> orderedProductArrayList;

    @Override
    protected ActivityOrderDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityOrderDetailsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderedProductArrayList = new ArrayList<>();
        adapter = new OrderedProductListAdapter(orderedProductArrayList);

        binding.orderProductRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.orderProductRecyclerView.setAdapter(adapter);
        int orderId = getIntent().getIntExtra("order", 0);
        if (orderId != 0)
            getOrder(orderId);
    }

    private void getOrder(int orderId) {

        Call<Order> call = mainApi.getOrder(orderId);

        call.enqueue(new RequestCallback<Order>() {
            @Override
            protected void onResponseSuccess(Call<Order> call, Response<Order> response) {
                onBind(response.body());
            }

            @Override
            protected void onResponseFailed(Call<Order> call, Throwable t) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void onBind(Order order) {

        binding.tvOrderNumber.setText(order.getOrderNumber());
        binding.tvOrderStatus.setText(order.getOrderStatus());
        binding.tvOrderPayment.setText(order.getPayment());
        binding.tvOrderTotalPrice.setText(order.getTotalCurrentPrice().toString());
        binding.tvUserName.setText(order.getName());
        binding.tvOrderPhoneNumber.setText(order.getPhone());
        binding.tvOrderedAddress.setText(order.getAddress());

        orderedProductArrayList.addAll(order.getProducts());
        adapter.notifyDataSetChanged();

    }


}
