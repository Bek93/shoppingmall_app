package com.beknumonov.shoppingmall_app.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.beknumonov.shoppingmall_app.base.BaseFragment;
import com.beknumonov.shoppingmall_app.databinding.FragmentCartBinding;
import com.beknumonov.shoppingmall_app.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    @Override
    protected FragmentProfileBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean toAttach) {
        return FragmentProfileBinding.inflate(inflater, parent, toAttach);
    }
}
