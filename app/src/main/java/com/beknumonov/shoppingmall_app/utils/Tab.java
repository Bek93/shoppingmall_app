package com.beknumonov.shoppingmall_app.utils;

public enum Tab {
    HOME("HOME"), PRODUCTS("PRODUCTS"), CART("CART"), PROFILE("PROFILE");

    private String tag;

    public String getTag() {
        return tag;
    }

    Tab(String tag) {
        this.tag = tag;
    }
}
