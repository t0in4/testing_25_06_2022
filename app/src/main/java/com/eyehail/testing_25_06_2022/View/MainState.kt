package com.eyehail.testing_25_06_2022.View

import com.eyehail.testing_25_06_2022.Model.Movie
import com.eyehail.testing_25_06_2022.Model.Result

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Movies(
        val movies: Movie
    ) : MainState()

    data class Error(val error: String?) : MainState()
}
