
package com.beknumonov.shoppingmall_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subproduct")
    @Expose
    private int subproduct;
    @SerializedName("price_current")
    @Expose
    private int priceCurrent;
    @SerializedName("price_original")
    @Expose
    private int priceOriginal;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("infinite")
    @Expose
    private boolean infinite;
    @SerializedName("has_option")
    @Expose
    private boolean hasOption;
    @SerializedName("image")
    @Expose
    private ProductImage image;
    @SerializedName("is_active")
    @Expose
    private boolean isActive;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("made_in")
    @Expose
    private String madeIn;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Product() {
    }

    /**
     * 
     * @param image
     * @param quantity
     * @param infinite
     * @param description
     * @param subproduct
     * @param title
     * @param isActive
     * @param priceCurrent
     * @param hasOption
     * @param priceOriginal
     * @param id
     * @param madeIn
     * @param brand
     */
    public Product(int id, String title, String description, int subproduct, int priceCurrent, int priceOriginal, int quantity, boolean infinite, boolean hasOption, ProductImage image, boolean isActive, String brand, String madeIn) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.subproduct = subproduct;
        this.priceCurrent = priceCurrent;
        this.priceOriginal = priceOriginal;
        this.quantity = quantity;
        this.infinite = infinite;
        this.hasOption = hasOption;
        this.image = image;
        this.isActive = isActive;
        this.brand = brand;
        this.madeIn = madeIn;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(int subproduct) {
        this.subproduct = subproduct;
    }

    public int getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(int priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public int getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(int priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    public boolean isHasOption() {
        return hasOption;
    }

    public void setHasOption(boolean hasOption) {
        this.hasOption = hasOption;
    }

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

}
