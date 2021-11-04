package id.rrdev.androidmade.movies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import id.rrdev.androidmade.R
import id.rrdev.androidmade.databinding.MoviesFragmentBinding
import id.rrdev.androidmade.detail.DetailActivity
import id.rrdev.core.data.Resource
import id.rrdev.core.domain.Movie
import id.rrdev.core.ui.*
import id.rrdev.core.utils.SortUtils
import id.rrdev.core.utils.hide
import id.rrdev.core.utils.show
import id.rrdev.core.utils.toast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class MoviesFragment : Fragment() {

    private var moviesFragmentBinding: MoviesFragmentBinding? = null
    private val binding get() = moviesFragmentBinding!!

    private val viewModel: MoviesViewModel by viewModel()

    //adapter
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter
    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    //carousel
    private lateinit var viewPager: ViewPager2

    //autoScroll
    private var scrollHandler = Handler(Looper.getMainLooper())
    private val SCROLL_DELAY = 5000L
    var scrollRunnable = Runnable {
        with(binding) {
            val setCurrent = rvCarousel.currentItem + 1
            rvCarousel.currentItem = setCurrent
            if ((setCurrent) == carouselAdapter.itemCount) {
                rvCarousel.currentItem = 0
            }
        }
    }

    //sort
    private var sort = SortUtils.RANDOM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        moviesFragmentBinding = MoviesFragmentBinding.inflate(inflater, container, false)
        additionalView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        setupView()
    }

    private fun initView() {
        carouselAdapter = CarouselAdapter()
        tvShowAdapter = TvShowAdapter()
        popularAdapter = PopularAdapter()
        topRatedAdapter = TopRatedAdapter()
        nowPlayingAdapter = NowPlayingAdapter()
    }

    private fun setupView() {

        setList(sort)

        with(binding) {
            viewPager = rvCarousel
            with(viewPager) {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = carouselAdapter
                offscreenPageLimit = 3
            }

            with(rvTvShow) {
                layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
                adapter = tvShowAdapter
            }

            with(rvPopular) {
                layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
                adapter = popularAdapter
            }

            with(rvTopRated) {
                layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
                adapter = topRatedAdapter
            }

            with(rvNowPlaying) {
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

        carouselAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        tvShowAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        popularAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        topRatedAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }

        nowPlayingAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
            startActivity(intent)
        }
    }

    private fun setList(sort: String) {
        viewModel.getDiscoverMovie(1, sort).observe(viewLifecycleOwner, discoverMovie)
        viewModel.getTvShowMovie(1, sort).observe(viewLifecycleOwner, tvShowMovie)
        viewModel.getPopularMovie(2, sort).observe(viewLifecycleOwner, popularMovie)
        viewModel.getTopRatedMovie(1, sort).observe(viewLifecycleOwner, topRatedMovie)
        viewModel.getNowPlayingMovie(1, sort).observe(viewLifecycleOwner, nowPlayingMovie)
    }

    private val discoverMovie = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                    carouselAdapter.setData(movies.data)
                }

                is Resource.Error -> {
                    binding.progressBar.hide()
                    binding.notFound.show()
                    binding.notFoundText.show()
                    context?.toast("Terjadi Kesalahan")
                }
            }
        }
    }

    private val tvShowMovie = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                    tvShowAdapter.setData(movies.data)
                }

                is Resource.Error -> {
                    binding.progressBar.hide()
                    binding.notFound.show()
                    binding.notFoundText.show()
                    context?.toast("Terjadi Kesalahan")
                }
            }
        }
    }

    private val popularMovie = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                    popularAdapter.setData(movies.data)
                }

                is Resource.Error -> {
                    binding.progressBar.hide()
                    binding.notFound.show()
                    binding.notFoundText.show()
                    context?.toast("Terjadi Kesalahan")
                }
            }
        }
    }

    private val topRatedMovie = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                    topRatedAdapter.setData(movies.data)
                }

                is Resource.Error -> {
                    binding.progressBar.hide()
                    binding.notFound.show()
                    binding.notFoundText.show()
                    context?.toast("Terjadi Kesalahan")
                }
            }
        }
    }

    private val nowPlayingMovie = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    binding.notFound.hide()
                    binding.notFoundText.hide()
                    nowPlayingAdapter.setData(movies.data)
                }

                is Resource.Error -> {
                    binding.progressBar.hide()
                    binding.notFound.show()
                    binding.notFoundText.show()
                    context?.toast("Terjadi Kesalahan")
                }
            }
        }
    }

    //setup view carousel
    private fun setupCarousel() {
        val compositePageTransformer = CompositePageTransformer()
        val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()

        compositePageTransformer.addTransformer { page, position ->
            val myOffset: Float = position * -(2 * pageOffset + pageMargin)
            val r = 1 - kotlin.math.abs(position)
            if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -myOffset
                } else {
                    page.translationX = myOffset
                }
            } else {
                page.translationY = myOffset
            }
            page.scaleY = 0.85f + r * 0.15f
        }

        binding.rvCarousel.apply {
            setPageTransformer(compositePageTransformer)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    scrollHandler.removeCallbacks(scrollRunnable)
                    scrollHandler.postDelayed(scrollRunnable, SCROLL_DELAY)
                }
            })
        }
    }

    private fun additionalView() {
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    override fun onPause() {
        super.onPause()
        scrollHandler.removeCallbacksAndMessages(null)
    }

    override fun onResume() {
        super.onResume()
        setupCarousel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        moviesFragmentBinding = null
    }

}