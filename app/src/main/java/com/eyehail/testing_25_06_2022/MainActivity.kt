package com.eyehail.testing_25_06_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eyehail.testing_25_06_2022.Api.MovieService
import com.eyehail.testing_25_06_2022.View.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private var adapter = MovieListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupObservables()
    }

    private fun setupUI() {
        mainViewModel = ViewModelProviders
            .of(this, ViewModelFactory(MovieService.api))
            .get(MainViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
            )

        }
        recyclerView.adapter = adapter
        /*buttonFetchMovies.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchMovies)
            }
        }*/
        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchMovies)
        }
    }

    private fun setupObservables() {
        lifecycleScope.launch {
            mainViewModel.state.collect { collector ->
                when (collector) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        buttonFetchMovies.visibility = View.GONE
                        progressBar.visibility = View.GONE
                    }
                    is MainState.Movies -> {
                        progressBar.visibility = View.GONE
                        buttonFetchMovies.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        collector.movies.let {
                            adapter.newMovies(it)
                        }
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchMovies.visibility = View.GONE
                        Toast.makeText(this@MainActivity, collector.error, Toast.LENGTH_SHORT).show()
                        Log.e("Error", "${collector.error}")
                    }
                }

            }
        }
    }
}