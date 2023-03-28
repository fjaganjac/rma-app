package com.example.rma_spirala

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(
    private var titles: List<String>,
    private var platforms: List<String>,
    private var images: List<Int>
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.movieTitle)
        val itemPlatform: TextView = itemView.findViewById(R.id.gamePlatform)
        val itemPicture: ImageView = itemView.findViewById(R.id.movieImage)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemPlatform.text = platforms[position]
        holder.itemPicture.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}