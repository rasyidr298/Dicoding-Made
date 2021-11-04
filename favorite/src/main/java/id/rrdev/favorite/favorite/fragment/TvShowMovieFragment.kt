package id.rrdev.favorite.favorite.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.androidmade.detail.DetailActivity
import id.rrdev.core.domain.Movie
import id.rrdev.core.ui.NowPlayingAdapter
import id.rrdev.core.utils.SortUtils
import id.rrdev.core.utils.snackbar
import id.rrdev.favorite.databinding.FragmentTvShowMovieBinding
import id.rrdev.favorite.favorite.FavoriteViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowMovieFragment : Fragment() {

    private var _fragmentFavoriteMoviesBinding: FragmentTvShowMovieBinding? = null
    private val binding get() = _fragmentFavoriteMoviesBinding!!

    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private val viewModel: FavoriteViewModel by viewModel()
    private var sort = SortUtils.RANDOM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragmentFavoriteMoviesBinding =  FragmentTvShowMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        setupView()
    }

    private fun initView() {
        nowPlayingAdapter = NowPlayingAdapter()
    }

    private fun setupView() {
        setList(sort)

        with(binding) {
            itemTouchHelper.attachToRecyclerView(rvFavorite)

            progressBar.visibility = View.VISIBLE
            notFound.visibility = View.GONE
            notFoundText.visibility = View.GONE

            with(rvFavorite) {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                adapter = nowPlayingAdapter
            }

            random.setOnClickListener {
                binding.menu.close(true)
                sort = SortUtils.RANDOM
                setList(sort)
            }

            newest.setOnClickListener {
                binding.menu.close(true)
                sort = SortUtils.NEWEST
                setList(sort)
            }

            popularity.setOnClickListener {
                binding.menu.close(true)
                sort = SortUtils.POPULARITY
                setList(sort)
            }

            vote.setOnClickListener {
                binding.menu.close(true)
                sort = SortUtils.VOTE
                setList(sort)
            }
        }

        nowPlayingAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movie = nowPlayingAdapter.getSwipedData(swipedPosition)
                var state = movie.favorite
                viewModel.setFavorite(movie, !state)
                state = !state
                binding.root.snackbar("Batalkan menghapus item sebelumnya") {
                    viewModel.setFavorite(movie, !state)
                }
            }
        }
    })

    private fun setList(sort: String) {
        viewModel.getFavoriteTvShows(sort).observe(this, moviesObserver)
    }

    private val moviesObserver = Observer<List<Movie>> { movies ->
        if (movies.isNullOrEmpty()){
            with(binding) {
                progressBar.visibility = View.GONE
                notFound.visibility = View.VISIBLE
                notFoundText.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                progressBar.visibility = View.GONE
                notFound.visibility = View.GONE
                notFoundText.visibility = View.GONE
            }
        }
        nowPlayingAdapter.setData(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteMoviesBinding = null
    }
}