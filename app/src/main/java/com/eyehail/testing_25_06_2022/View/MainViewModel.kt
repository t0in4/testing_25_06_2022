package com.eyehail.testing_25_06_2022.View

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyehail.testing_25_06_2022.Api.MovieRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainViewModel(private val repo: MovieRepo):ViewModel() {
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
    get()= _state
    init {
        handleIntent()
    }
    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{ collector ->
                when (collector) {
                    is MainIntent.FetchMovies -> fetchMovies()
                }
            }
        }
    }
    private fun fetchMovies() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Movies(repo.getMovies())

            } catch(e: Exception) {
                Log.e("MainViewModel", "${e.localizedMessage}")
                MainState.Error(e.localizedMessage)
            }
        }
    }
}