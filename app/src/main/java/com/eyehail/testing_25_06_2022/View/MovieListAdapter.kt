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
import com.google.gson.Gson
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListAdapter(private val movies: ArrayList<Result>) :
    RecyclerView.Adapter<MovieListAdapter.DataViewHolder>() {
        class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(movie: Result) {

                itemView.movieName.text = movie.display_title
                itemView.movieReview.text = movie.summary_short
                val url = movie.multimedia.src
                Glide.with(itemView.movieImage.context)
                    .load(url)
                    .into(itemView.movieImage)
            }
        }
/*    var gson = Gson()
    var data = gson.fromJson(movies, Array<Movie>::class.java)
    for (x in 0..data.size) {
        println(data[x].results)
    }*/


    override fun getItemCount() = movies.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun newMovies(newMovies: List<Result>) {
        movies.clear()
        movies.addAll(newMovies)
        //this.notifyItemInserted(movies.size-1)
        notifyDataSetChanged()
    }



}


