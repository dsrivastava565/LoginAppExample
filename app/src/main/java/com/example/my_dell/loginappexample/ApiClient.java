package com.example.my_dell.loginappexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://10.21.67.87/loginapp/";

public static Retrofit retrofit = null;

  public  static Retrofit getApiClient()
  {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
      httpClient.addInterceptor(logging);  // <-- this is the important line!



      if (retrofit==null)
      {
          Gson gson = new GsonBuilder()
                  .setLenient()
                  .create();

          retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                  .client(httpClient.build())
                  .build();

      }
      return retrofit;

  }


}
