package com.example.nasa_apod_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasa_apod_app.R
import com.example.nasa_apod_app.model.GalleryInfo

/**
 * Adapter for the [RecyclerView] in [MainFragment]. Displays [GalleryInfo] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val dataset: List<GalleryInfo>,
    private var onItemClicked: ((position: Int) -> Unit)
) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]
        holder.tvTitle.text = item.title

        Glide
            .with(context)
            .load(item.hdurl)
            .centerCrop()
            .thumbnail(Glide.with(context).load(R.drawable.loading_spinner))
            .into(holder.imageView);

        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }

    /**
     * Return the size of dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size
}


