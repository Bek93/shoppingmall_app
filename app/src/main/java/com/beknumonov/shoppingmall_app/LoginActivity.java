package com.beknumonov.shoppingmall_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.beknumonov.shoppingmall_app.base.BaseActivity;
import com.beknumonov.shoppingmall_app.databinding.ActivityLoginBinding;
import com.beknumonov.shoppingmall_app.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected ActivityLoginBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // How to show verified email icon:
        // 1. Check email:
        //    1. Email needs to contain '@' and index of '@' should be over than 0  i@gmail.com
        //       Invalid input: @gmail.com
        //    2. Email needs to contain '.' after '@' index
        //       Invalid input: 1. test@.com  2.  test@gmail.
        // We check it in two place: 1. Login button clicked. 2. While input text by user.

        // Listen input text
        binding.etEmail.addTextChangedListener(new TextWatcher() {

            // before text change
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("EditText", s.toString() + " start=" + start + ", count=" + count + ", after=" + after);
            }

            // on text change: s is input char
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("EditText", s.toString() + " start=" + start + ", count=" + count + ", before=" + before);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = s.toString();
                Log.d("EditText", email);

                /*if (email.contains("@") && email.indexOf('@') != 0) {
                    String tail = email.substring(email.indexOf('@'));
                    if (tail.contains(".")
                            && tail.indexOf('.') != 1
                            && email.lastIndexOf('.') != (email.length() - 1)) {
                        binding.icVerifiedEmail.setVisibility(View.VISIBLE);
                    } else {
                        binding.icVerifiedEmail.setVisibility(View.INVISIBLE);
                    }
                } else {
                    binding.icVerifiedEmail.setVisibility(View.INVISIBLE);
                }*/

                /* if (isEmailValid(email)) {
                    binding.icVerifiedEmail.setVisibility(View.VISIBLE);
                } else {
                    binding.icVerifiedEmail.setVisibility(View.INVISIBLE);
                }*/

                binding.icVerifiedEmail.setVisibility(isEmailValid(email) ? View.VISIBLE : View.INVISIBLE);

            }
        });


        binding.showHidePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = binding.showHidePasswordBtn.isSelected();

                // current state: iSelected => true (shown password) next state: !iSelected => false (hide password)
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                // initial states: isSelected=> false (password is hidden). When eye is clicked, change shown password mode.
                // current state: iSelected => false (hidden password) next state: !iSelected => true (show password)

                if (!isSelected) {
                    // show password
                    //binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT);

                } else {
                    // hide password
                    //binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                binding.showHidePasswordBtn.setSelected(!isSelected);

            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();

                // check if email is valid
                if (!isEmailValid(email))
                    return;


                Call<User> call = mainApi.login(new User(email, password));

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            preferenceManager.setValue("isLoggedIn", true);
                            preferenceManager.setValue("email", email);
                            preferenceManager.setValue("password", password);
                            preferenceManager.setValue("access_token", user.getAccessToken());
                            preferenceManager.setValue("user", user);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });




            }
        });

        binding.moveToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean isEmailValid(String email) {
        boolean isValid = false;
        if (email.contains("@") && email.indexOf('@') != 0) {
            String tail = email.substring(email.indexOf('@'));
            if (tail.contains(".")
                    && tail.indexOf('.') != 1
                    && email.lastIndexOf('.') != (email.length() - 1)) {
                isValid = true;
            }
        }

        return isValid;
    }
}
