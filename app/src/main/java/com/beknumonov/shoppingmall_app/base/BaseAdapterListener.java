package com.beknumonov.shoppingmall_app.base;

import com.beknumonov.shoppingmall_app.adapter.ClassificationAdapter;

public interface BaseAdapterListener {

    void onCategoryClick(int id, String title, ClassificationAdapter.Type type);
}
