package com.beknumonov.shoppingmall_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.shoppingmall_app.base.BaseAdapter;
import com.beknumonov.shoppingmall_app.base.BaseViewHolder;
import com.beknumonov.shoppingmall_app.databinding.ItemCartBinding;
import com.beknumonov.shoppingmall_app.databinding.ItemOrderedProductBinding;
import com.beknumonov.shoppingmall_app.model.Cart;
import com.beknumonov.shoppingmall_app.model.OrderedProduct;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class OrderedProductListAdapter extends BaseAdapter {

    private ArrayList<OrderedProduct> orderedProductArrayList;

    public OrderedProductListAdapter(ArrayList<OrderedProduct> orderedProductArrayList) {
        this.orderedProductArrayList = orderedProductArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderedProductBinding binding = ItemOrderedProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderedProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return orderedProductArrayList.size();
    }

    class OrderedProductViewHolder extends BaseViewHolder<ItemOrderedProductBinding> {


        public OrderedProductViewHolder(ItemOrderedProductBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            OrderedProduct orderedProduct = orderedProductArrayList.get(position);

            binding.productTitle.setText(orderedProduct.getProduct().getTitle());
            binding.colorOption.setText(orderedProduct.getOption().getColorOption().getTitle());
            binding.sizeOption.setText(orderedProduct.getOption().getSizeOption().getTitle());
            binding.price.setText(orderedProduct.getProduct().getPriceCurrent());
            binding.quantity.setText(String.valueOf(orderedProduct.getQuantity()));

            Glide.with(binding.productImage).load(orderedProduct.getProduct().getImage().getImage()).into(binding.productImage);

            binding.totalPrice.setText(orderedProduct.getProduct().getProductTotalPrice(orderedProduct.getQuantity()));
        }
    }


}
