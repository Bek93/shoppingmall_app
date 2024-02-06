package com.beknumonov.shoppingmall_app.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.beknumonov.shoppingmall_app.base.BaseFragment;
import com.beknumonov.shoppingmall_app.databinding.FragmentCartBinding;
import com.beknumonov.shoppingmall_app.databinding.FragmentHomeBinding;

public class CartFragment extends BaseFragment<FragmentCartBinding> {
    @Override
    protected FragmentCartBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentCartBinding.inflate(inflater, parent, toAttach);
    }
}
