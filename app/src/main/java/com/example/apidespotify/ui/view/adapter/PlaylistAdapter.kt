package com.example.apidespotify.ui.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.apidespotify.R
import com.example.apidespotify.data.models.Item
import java.lang.Exception

class PlaylistAdapter(
    private val listaDatos: List<Item>,
    private val itemClick: (Item) -> Unit): RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var image: ImageView = itemView.findViewById(R.id.image)
        var cardView: CardView = itemView.findViewById(R.id.cardView)

        fun bind(position: Int, item: Item, itemClick: (Item) -> Unit){
            cardView.setOnClickListener { itemClick(item) }

            try {
                Glide.with(itemView)
                    .load(item.getImages()[0].getUrl())
                    .into(image)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, null, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, listaDatos[position], itemClick)
    }

    override fun getItemCount(): Int {
        return listaDatos.size
    }
}