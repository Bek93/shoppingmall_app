package com.beknumonov.shoppingmall_app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.beknumonov.shoppingmall_app.base.BaseAdapter;
import com.beknumonov.shoppingmall_app.base.BaseViewHolder;
import com.beknumonov.shoppingmall_app.databinding.ItemDotBinding;
import com.beknumonov.shoppingmall_app.model.Banner;

import java.util.ArrayList;

public class IndicatorAdapter extends BaseAdapter {

    private int size;
    private int selectedDotPosition = 0;

    public void setSize(int size) {
        this.size = size;
    }

    public int getSelectedDotPosition() {
        return selectedDotPosition;
    }

    public void setSelectedDotPosition(int selectedDotPosition) {
        this.selectedDotPosition = selectedDotPosition;
    }

    public IndicatorAdapter(int size) {
        this.size = size;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDotBinding binding = ItemDotBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DotViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    class DotViewHolder extends BaseViewHolder<ItemDotBinding> {

        public DotViewHolder(ItemDotBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.dot.setSelected(selectedDotPosition == position);
        }
    }
}
