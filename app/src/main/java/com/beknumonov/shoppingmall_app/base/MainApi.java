package com.beknumonov.shoppingmall_app.base;

import com.beknumonov.shoppingmall_app.model.Banner;
import com.beknumonov.shoppingmall_app.model.Cart;
import com.beknumonov.shoppingmall_app.model.CartRequest;
import com.beknumonov.shoppingmall_app.model.Category;
import com.beknumonov.shoppingmall_app.model.Classification;
import com.beknumonov.shoppingmall_app.model.Order;
import com.beknumonov.shoppingmall_app.model.PreOrder;
import com.beknumonov.shoppingmall_app.model.Product;
import com.beknumonov.shoppingmall_app.model.ProductList;
import com.beknumonov.shoppingmall_app.model.Subproduct;
import com.beknumonov.shoppingmall_app.model.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {


    @POST("/v1/login/")
    Call<User> login(@Body User user);

    @POST("/v1/user/")
    Call<User> createUser(@Body User user);

    @GET("/v1/user/verify_email/")
    Call<JsonObject> requestVerifyEmail(@Query("email") String email);


    @GET("/v1/classification/")
    Call<ArrayList<Classification>> getClassifications();


    @GET("/v1/classification/{id}/category/")
    Call<ArrayList<Category>> getCategoryWithClassification(@Path("id") int classificationId);


    @GET("/v1/category/{id}/subproduct/")
    Call<ArrayList<Subproduct>> getSubproductWithCategory(@Path("id") int categoryId);


    // Home Page

    @GET("/v1/banner/")
    Call<ArrayList<Banner>> getBanners();

    @GET("/v1/popular/")
    Call<ArrayList<Product>> getPopularProducts();

    // Products

    @GET("/v1/subproduct/{id}/products/")
    Call<ArrayList<Product>> getProducts(@Path("id") int subproductId);

    @GET("/v1/product/{id}/")
    Call<Product> getProductDetails(@Path("id") int productId);


    @POST("/v1/cart/")
    Call<CartRequest> addProductToCart(@Body CartRequest cartRequest);

    @GET("/v1/cart/")
    Call<ArrayList<Cart>> getMyCart();

    @POST("/v1/cart/order/")
    Call<Order> orderCarts(@Body PreOrder preOrder);
}
