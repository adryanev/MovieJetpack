package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.databinding.ItemMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.detail.DetailActivity
import dev.adryanev.dicoding.moviejetpack.ui.home.MovieCallback

class MovieAdapter(private val callback: MovieCallback) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                itemMovieTitle.text = movie.title
                itemMovieReleaseDate.text = movie.releaseDate
                itemMovieScore.text = (movie.userScore.toFloat() / 10).toString()
                itemMovieShare.setOnClickListener { callback.onShareClick(movie) }
                val posterId: Int = binding.root.resources.getIdentifier(
                    movie.poster,
                    "drawable",
                    binding.root.context.packageName
                )
                Glide.with(itemView.context)
                    .load(ContextCompat.getDrawable(binding.root.context, posterId))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(itemMoviePoster)
                itemView.setOnClickListener {
                   callback.onItemClick(movie)

                }

            }
        }
    }

    var listMovies = ArrayList<MovieEntity>()
    fun setMovies(movies: List<MovieEntity>) {
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

}
