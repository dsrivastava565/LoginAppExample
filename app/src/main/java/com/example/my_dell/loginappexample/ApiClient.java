package com.example.my_dell.loginappexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://127.0.0.1/loginapp/";

public static Retrofit retrofit = null;


  public  static Retrofit getApiClient()
  {
      if (retrofit==null)
      {
          retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

      }
      return retrofit;

  }


}
