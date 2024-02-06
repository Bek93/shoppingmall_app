package com.beknumonov.shoppingmall_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.beknumonov.shoppingmall_app.base.BaseActivity;
import com.beknumonov.shoppingmall_app.databinding.ActivityMainBinding;
import com.beknumonov.shoppingmall_app.fragments.CartFragment;
import com.beknumonov.shoppingmall_app.fragments.HomeFragment;
import com.beknumonov.shoppingmall_app.fragments.ProductsFragment;
import com.beknumonov.shoppingmall_app.fragments.ProfileFragment;
import com.beknumonov.shoppingmall_app.utils.Tab;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private Tab currentTab;
    private HomeFragment homeFragment;
    private ProductsFragment productsFragment;
    private CartFragment cartFragment;
    private ProfileFragment profileFragment;

    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentTab = Tab.HOME;
        onNavigationTabSelected(currentTab);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homeTab:
                        onNavigationTabSelected(Tab.HOME);
                        break;
                    case R.id.productsTab:
                        onNavigationTabSelected(Tab.PRODUCTS);
                        break;
                    case R.id.cartTab:
                        onNavigationTabSelected(Tab.CART);
                        break;
                    case R.id.profileTab:
                        onNavigationTabSelected(Tab.PROFILE);
                        break;
                }

                return true;
            }
        });

    }

    private void onNavigationTabSelected(Tab tab) {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(currentTab.getTag());

        if (fragment != null)
            getSupportFragmentManager().beginTransaction().hide(fragment).commit();
        currentTab = tab;
        switch (tab) {

            case HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, homeFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                }

                setTitle("Home");
                break;

            case PRODUCTS:

                if (productsFragment == null) {
                    productsFragment = new ProductsFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, productsFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(productsFragment).commit();
                }
                setTitle("Products");
                break;

            case CART:

                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, cartFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(cartFragment).commit();
                }

                setTitle("Cart");

                break;

            case PROFILE:

                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, profileFragment, tab.getTag()).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(profileFragment).commit();
                }
                setTitle("Profile");
                break;
        }
    }


}