package com.beknumonov.shoppingmall_app.model;

import com.google.gson.annotations.SerializedName;

public class Classification {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("is_active")
    private Boolean isActive;

    public Classification() {
    }

    public Classification(int id, String title, Boolean isActive) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
