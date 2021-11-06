package id.rrdev.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.rrdev.core.R
import id.rrdev.core.databinding.ItemCarouselBinding
import id.rrdev.core.domain.Movie
import id.rrdev.core.utils.DiffUtils
import id.rrdev.core.utils.loadImage

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.MyViewHolder>() {

    var list = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        val diffUtilCallback = DiffUtils(list, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemCardListBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemCardListBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(private val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding){
                tvName.text = data.title

                ivAvatar.loadImage(itemView.context.getString(R.string.baseUrlImage, data.posterPath))
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }

    }
}
