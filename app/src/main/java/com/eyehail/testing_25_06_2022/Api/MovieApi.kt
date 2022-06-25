package com.eyehail.testing_25_06_2022.Api

import com.eyehail.testing_25_06_2022.Model.Movie
import com.eyehail.testing_25_06_2022.Model.Result
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
data class CharactersResponse(
    @SerializedName("results")
    val moviesList: Movie
)

interface MovieApi {
    @GET("all.json?api-key=TW1AZ6D0T2OdB1n8i7dHvBaB9wjWZD67")

    suspend fun getMovies(): Movie
}