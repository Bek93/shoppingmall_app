package com.beknumonov.shoppingmall_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PreOrder {

    @SerializedName("carts")
    private ArrayList<Integer> carts;

    public PreOrder(ArrayList<Integer> carts) {
        this.carts = carts;
    }

    public ArrayList<Integer> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Integer> carts) {
        this.carts = carts;
    }
}
