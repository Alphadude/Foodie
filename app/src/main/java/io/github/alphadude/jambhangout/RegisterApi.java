package io.github.alphadude.jambhangout;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


/**
 * Created by alphadude on 8/23/17.
 */

public interface RegisterApi {
}
/*

    //@FormUrlEncoded;
    @POST("/module/newuser")

     void insertUser(
                    @Field("name") String fullname,
                    @Field("username") String username,
                    @Field("email") String email,
                    @Field("password") String password
                            Callback<Response> callback);
}
/*
    @GET("api/{email}/{password}")
    Call<Login> authenticate(@Path("email") String email,
                             @Path("password") String password);

    @POST("api/{email}/{password}")
    Call<Login> registration(@Path("email") String email,
                             @Path("password") String password);
*/
