package com.beknumonov.shoppingmall_app.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ColorOption implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public ColorOption(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public ColorOption() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this.id == ((ColorOption) obj).getId();
    }
}
