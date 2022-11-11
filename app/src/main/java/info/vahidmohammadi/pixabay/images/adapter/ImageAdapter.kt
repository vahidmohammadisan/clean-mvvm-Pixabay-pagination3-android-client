package info.vahidmohammadi.pixabay.images.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import info.vahidmohammadi.domain.model.Image
import info.vahidmohammadi.pixabay.databinding.ListItemBinding
import info.vahidmohammadi.pixabay.images.OnItemClickListener

class ImageAdapter(private val onItemClickListener: OnItemClickListener) :
    PagingDataAdapter<Image, ImageAdapter.ViewHolder>(DataDifferntiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.image.load(getItem(position)?.previewURL)

        holder.view.card.setOnClickListener {
            onItemClickListener.onItemClicked(getItem(position)!!.id)
        }

    }

    inner class ViewHolder(var view: ListItemBinding) : RecyclerView.ViewHolder(view.root)

    object DataDifferntiator : DiffUtil.ItemCallback<Image>() {

        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

}
