package com.example.rma_spirala

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(
    private var games: List<Game>,
    private var listener: RecyclerViewEvent
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val itemTitle: TextView = itemView.findViewById(R.id.item_title_textview)
        val itemRating: TextView = itemView.findViewById(R.id.game_rating_textview)
        val itemReleaseDate: TextView = itemView.findViewById(R.id.game_release_date_textview)
        val itemPlatform: TextView = itemView.findViewById(R.id.game_platform_textview)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = bindingAdapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = games[position].title
        holder.itemRating.text = games[position].rating.toString()
        holder.itemReleaseDate.text = games[position].releaseDate
        holder.itemPlatform.text = games[position].platform

    }

    override fun getItemCount(): Int {
        return games.size
    }

    interface RecyclerViewEvent {
        fun onItemClick(position: Int)
    }
}