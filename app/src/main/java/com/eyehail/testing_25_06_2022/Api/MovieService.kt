package com.eyehail.testing_25_06_2022.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieService {
    const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/"
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: MovieApi = getRetrofit().create(MovieApi::class.java)
}