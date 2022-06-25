package com.eyehail.testing_25_06_2022.Api

class MovieRepo(private val api: MovieApi) {
    suspend fun getMovies() = api.getMovies()
}