package id.rrdev.favorite.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import id.rrdev.favorite.SectionsPagerAdapter
import id.rrdev.favorite.databinding.FavoriteFragmentBinding
import id.rrdev.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

@Keep
class FavoriteFragment : Fragment() {

    private var _fragmentFavoriteMoviesBinding: FavoriteFragmentBinding? = null
    private val binding get() = _fragmentFavoriteMoviesBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _fragmentFavoriteMoviesBinding =  FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {
        loadKoinModules(favoriteModule)

        with(binding) {
            val sectionsPagerAdapter = SectionsPagerAdapter(context as Context, childFragmentManager)
            viewPager.adapter = sectionsPagerAdapter
            tab.setupWithViewPager(binding.viewPager)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteMoviesBinding = null
    }

}