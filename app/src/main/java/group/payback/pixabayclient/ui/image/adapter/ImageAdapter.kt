package group.payback.pixabayclient.ui.image.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import group.payback.domain.model.Image
import group.payback.pixabayclient.databinding.ListItemBinding
import group.payback.pixabayclient.ui.image.OnItemClickListener

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
