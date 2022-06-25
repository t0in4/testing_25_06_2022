package com.eyehail.testing_25_06_2022.View

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eyehail.testing_25_06_2022.Api.MovieApi
import com.eyehail.testing_25_06_2022.Api.MovieRepo

class ViewModelFactory(private val api: MovieApi): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MovieRepo(api)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}