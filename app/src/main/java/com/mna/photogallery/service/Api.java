package com.mna.photogallery.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mna.photogallery.model.Photos;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * API CALL of th Backend Server
 * Implemented on the Basis of singleton pattern
 */
public class Api {

    private static ApiInterface api;
    private static final String BASE_URL = "https://api.unsplash.com";

    /**
     *
     * @return
     */
    public static ApiInterface getApi() {
        if (api == null)
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(
                            Photos.class,
                            new JsonPhotosDeserializer())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            api = retrofit.create(ApiInterface.class);
        }
        return api;
    }

    public interface ApiInterface {
        @GET("/photos")
        Call<Photos> getPhotos(@Query("client_id") String client_id,@Query("page") String page);

        @GET("/photos/{id}")
        Call<Photos> getPhotoById(@Path("id") String id,@Query("client_id") String client_id);

    }
}