package com.beknumonov.shoppingmall_app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.beknumonov.shoppingmall_app.adapter.ProductListAdapter;
import com.beknumonov.shoppingmall_app.base.BaseActivity;
import com.beknumonov.shoppingmall_app.base.RequestCallback;
import com.beknumonov.shoppingmall_app.databinding.ActivityProductListBinding;
import com.beknumonov.shoppingmall_app.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends BaseActivity<ActivityProductListBinding> {

    private ProductListAdapter adapter;
    private ArrayList<Product> productArrayList;

    @Override
    protected ActivityProductListBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityProductListBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productArrayList = new ArrayList<>();
        adapter = new ProductListAdapter(productArrayList);
        int subproductId = getIntent().getIntExtra("id", 0);
        String title = getIntent().getStringExtra("title");
        if (title == null)
            title = "Products";
        setTitle(title);
        if (subproductId != 0) {
            loadProducts(subproductId);
        }

        binding.productListRecyclerView.setLayoutManager(new GridLayoutManager(ProductListActivity.this, 2));
        binding.productListRecyclerView.setAdapter(adapter);
    }


    private void loadProducts(int subproductId) {

        Call<ArrayList<Product>> call = mainApi.getProducts(subproductId);


        call.enqueue(new RequestCallback<ArrayList<Product>>() {
            @Override
            protected void onResponseSuccess(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                productArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            protected void onResponseFailed(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Request is failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }
}
