package id.rrdev.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.core.R
import id.rrdev.core.databinding.ItemMovieVerticalBinding
import id.rrdev.core.domain.Movie
import id.rrdev.core.utils.DiffUtils
import id.rrdev.core.utils.loadImage
import java.util.*

class NowPlayingAdapter : RecyclerView.Adapter<NowPlayingAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        val diffUtilCallback = DiffUtils(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getSwipedData(swipedPosition: Int): Movie = listData[swipedPosition]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemCardListBinding =
            ItemMovieVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemCardListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class MovieViewHolder(private val binding: ItemMovieVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                title.text = movie.title
                date.text = movie.releaseDate
                language.text = movie.originalLanguage
                popularity.text =
                    itemView.context.getString(
                        R.string.popularity_d,
                        movie.popularity.toString()
                    )
                userScore.text = movie.voteAverage.toString()

                poster.loadImage(itemView.context.getString(R.string.baseUrlImage, movie.posterPath))
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}