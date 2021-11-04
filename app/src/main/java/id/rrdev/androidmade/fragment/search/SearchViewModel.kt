package id.rrdev.androidmade.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.rrdev.core.data.Resource
import id.rrdev.core.domain.IMovieAppUseCase
import id.rrdev.core.domain.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val movieAppUseCase: IMovieAppUseCase) : ViewModel() {

    fun getSearchMovie(query: String, page: Int, sort: String): LiveData<Resource<List<Movie>>> {
        return movieAppUseCase.getSearchMovies(query, page, sort).asLiveData()
    }
}