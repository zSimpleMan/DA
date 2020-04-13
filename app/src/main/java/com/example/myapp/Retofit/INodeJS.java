package com.example.myapp.Retofit;

import io.reactivex.Observable;
import android.graphics.drawable.shapes.OvalShape;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJS {
    @POST("api/auth")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("username") String username,
                         @Field("password") String password);

    @POST("api/users")
    @FormUrlEncoded
    Observable<String> regisUser(@Field("username") String username,
                                 @Field("password") String password,
                                 @Field("email") String email);
}
