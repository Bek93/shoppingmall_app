package com.beknumonov.shoppingmall_app.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseViewHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {

    private BaseAdapterListener baseAdapterListener;
    protected VB binding;

    public BaseViewHolder(VB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setBaseAdapterListener(BaseAdapterListener baseAdapterListener) {
        this.baseAdapterListener = baseAdapterListener;
    }


    public abstract void onBind(int position);
}
