package com.beknumonov.shoppingmall_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beknumonov.shoppingmall_app.adapter.OptionListAdapter;
import com.beknumonov.shoppingmall_app.base.BaseDialog;
import com.beknumonov.shoppingmall_app.databinding.DialogColorBinding;
import com.beknumonov.shoppingmall_app.model.ColorOption;
import com.beknumonov.shoppingmall_app.model.SizeOption;

import java.util.ArrayList;

public class OptionDialog extends BaseDialog<DialogColorBinding> {

    private OptionListAdapter optionListAdapter;
    private ArrayList<ColorOption> colorOptionArrayList;
    private ArrayList<SizeOption> sizeOptionArrayList;
    private OptionItemListener optionItemListener;


    private ColorOption selectColorOption;
    private SizeOption selectSizeOption;

    public void setSelectColorOption(ColorOption selectColorOption) {
        this.selectColorOption = selectColorOption;
    }

    public void setSelectSizeOption(SizeOption selectSizeOption) {
        this.selectSizeOption = selectSizeOption;
    }

    public void setOptionItemListener(OptionItemListener optionItemListener) {
        this.optionItemListener = optionItemListener;
    }

    public void setColorOptionArrayList(ArrayList<ColorOption> colorOptionArrayList) {
        this.colorOptionArrayList = colorOptionArrayList;
    }

    public void setSizeOptionArrayList(ArrayList<SizeOption> sizeOptionArrayList) {
        this.sizeOptionArrayList = sizeOptionArrayList;
    }

    @Override
    protected DialogColorBinding inflateView(LayoutInflater inflater, ViewGroup parent, boolean attachedToRoot) {
        return DialogColorBinding.inflate(inflater, parent, attachedToRoot);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (colorOptionArrayList != null)
            binding.title.setText("Color");
        else
            binding.title.setText("Size");
        optionListAdapter = new OptionListAdapter(colorOptionArrayList, sizeOptionArrayList);
        optionListAdapter.setSelectColorOption(selectColorOption);
        optionListAdapter.setSelectSizeOption(selectSizeOption);
        optionListAdapter.setOptionItemListener(new OptionItemListener() {
            @Override
            public void onColorItemSelected(ColorOption colorOption) {
                optionItemListener.onColorItemSelected(colorOption);
                dismiss();

            }

            @Override
            public void onSizeItemSelected(SizeOption sizeOption) {

                optionItemListener.onSizeItemSelected(sizeOption);
                dismiss();
            }
        });
        binding.optionRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.optionRecycleView.setAdapter(optionListAdapter);

    }

    public interface OptionItemListener {
        void onColorItemSelected(ColorOption colorOption);

        void onSizeItemSelected(SizeOption sizeOption);
    }
}
