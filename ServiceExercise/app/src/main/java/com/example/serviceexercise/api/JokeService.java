package com.example.serviceexercise.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeService {
    @GET("jokes/random/")
    Call<NorrisQuote> getJoke();
}
