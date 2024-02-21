package com.beknumonov.shoppingmall_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.shoppingmall_app.base.BaseAdapter;
import com.beknumonov.shoppingmall_app.base.BaseViewHolder;
import com.beknumonov.shoppingmall_app.databinding.ItemCartBinding;
import com.beknumonov.shoppingmall_app.model.Cart;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
*     CartFragment ----> CartSelectionListener ----> CartListAdapter
*                                                                   Item 1  <----- CartSelectionListener  ---> CartSelectionListener.onSelectionChanged()
*                                                                   Item 2  <----- CartSelectionListener  ---> CartSelectionListener.onSelectionChanged()
*                                                                   Item 3  <----- CartSelectionListener  ---> CartSelectionListener.onSelectionChanged()
*                                                                   Item 4  <----- CartSelectionListener  ---> CartSelectionListener.onSelectionChanged()
*
*
* */



public class CartListAdapter extends BaseAdapter {

    private ArrayList<Cart> cartArrayList;

    private CartSelectListener cartSelectListener;

    public void setCartSelectListener(CartSelectListener cartSelectListener) {
        this.cartSelectListener = cartSelectListener;
    }

    public CartListAdapter(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (holder instanceof CartViewHolder)
            ((CartViewHolder) holder).setCartSelectListener(cartSelectListener);
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    class CartViewHolder extends BaseViewHolder<ItemCartBinding> {

        private CartSelectListener cartSelectListener;

        public void setCartSelectListener(CartSelectListener cartSelectListener) {
            this.cartSelectListener = cartSelectListener;
        }

        public CartViewHolder(ItemCartBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            Cart cart = cartArrayList.get(position);

            binding.productTitle.setText(cart.getProduct().getTitle());
            binding.colorOption.setText(cart.getOption().getColorOption().getTitle());
            binding.sizeOption.setText(cart.getOption().getSizeOption().getTitle());
            binding.price.setText(cart.getProduct().getPriceCurrent());
            binding.quantity.setText(String.valueOf(cart.getQuantity()));

            Glide.with(binding.productImage).load(cart.getProduct().getImage().getImage()).into(binding.productImage);

            binding.totalPrice.setText(cart.getProduct().getProductTotalPrice(cart.getQuantity()));

            binding.select.setSelected(cart.isSelected());
            binding.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isSelected = binding.select.isSelected();
                    binding.select.setSelected(!isSelected);
                    cart.setSelected(!isSelected);
                    if (cartSelectListener != null) {
                        cartSelectListener.onSelectorChanged();
                    }
                }
            });
        }
    }

    public interface CartSelectListener {
        void onSelectorChanged();
    }


}
