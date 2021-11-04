package id.rrdev.androidmade.fragment.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.miguelcatalan.materialsearchview.MaterialSearchView
import id.rrdev.androidmade.R
import id.rrdev.androidmade.databinding.MoviesFragmentBinding
import id.rrdev.androidmade.databinding.SearchFragmentBinding
import id.rrdev.androidmade.home.HomeActivity

class SearchFragment : Fragment() {

    private var binding: SearchFragmentBinding? = null

    private lateinit var searchView: MaterialSearchView

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        additionalView()
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun additionalView() {
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        searchView = (activity as HomeActivity).findViewById(R.id.search_view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)
    }

//    private val searchMovie = Observer<Resource<List<Movie>>> { movies ->
//        if (movies != null) {
//            when (movies) {
//                is Resource.Loading -> {
//                    binding.progressBar.show()
//                    binding.notFound.hide()
//                    binding.notFoundText.hide()
//                }
//                is Resource.Success -> {
//                    binding.progressBar.hide()
//                    binding.notFound.hide()
//                    binding.notFoundText.hide()
//                    Log.e("search", movies.data.toString())
////                    moviesAdapter.setData(movies.data)
//                }
//
//                is Resource.Error -> {
//                    binding.progressBar.hide()
//                    binding.notFound.show()
//                    binding.notFoundText.show()
//                    context?.toast("Terjadi Kesalahan")
//                }
//            }
//        }
//    }

//    private fun observeSearchQuery() {
//
//        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    searchViewModel.setSearchQuery(it)
//                }
//                return true
//            }
//        })
//    }

//    private fun setSearchList() {
//
//        searchViewModel.movieResult.observe(viewLifecycleOwner, { movies ->
//            if (movies.isNullOrEmpty()){
//                binding.progressBar.hide()
//                binding.notFound.show()
//                binding.notFoundText.show()
//            } else {
//                binding.progressBar.hide()
//                binding.notFound.hide()
//                binding.notFoundText.hide()
//            }
//            moviesAdapter.setData(movies)
//        })
//
//        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener{
//            override fun onSearchViewShown() {
//                binding.progressBar.hide()
//                binding.notFound.hide()
//                binding.notFoundText.hide()
//            }
//
//            override fun onSearchViewClosed() {
//                binding.progressBar.hide()
//                binding.notFound.hide()
//                binding.notFoundText.hide()
//                setList(sort)
//            }
//        })
//    }

}