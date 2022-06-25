package com.eyehail.testing_25_06_2022.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eyehail.testing_25_06_2022.MainActivity
import com.eyehail.testing_25_06_2022.Model.Movie
import com.eyehail.testing_25_06_2022.Model.Result
import com.eyehail.testing_25_06_2022.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.DataViewHolder>() {
        class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(movie: Movie) {

                itemView.movieName.text = movie.results.firstOrNull()?.display_title
                itemView.movieReview.text = movie.results.firstOrNull()?.summary_short
                val url = movie.results.firstOrNull()?.multimedia?.src
                Glide.with(itemView.movieImage.context)
                    .load(url)
                    .into(itemView.movieImage)
            }
        }

    override fun getItemCount() = movies.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun newMovies(newMovies: Movie) {
        movies.clear()
        movies.addAll(listOf(newMovies))
        //this.notifyItemInserted(movies.size-1)
        this.notifyDataSetChanged()
    }



}


