package com.group2.recipeze.data;

import com.google.gson.Gson;
import com.group2.recipeze.data.model.LoggedInUser;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    protected Retrofit retrofit;
    protected String username;
    protected Gson gson;

    public Repository() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://recipeze-backend.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginRepository loginRepository = LoginRepository.getInstance(new LoginDataSource());
        username = loginRepository.getUser().getUsername();
        gson = new Gson();
    }
}
