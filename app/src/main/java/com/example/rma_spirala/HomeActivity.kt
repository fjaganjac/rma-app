package com.example.rma_spirala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(), GameAdapter.RecyclerViewEvent {

    private var gamesList = getGames()

    private lateinit var gamesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        gamesRecyclerView = findViewById(R.id.gameRecyclerView)


        gamesRecyclerView.layoutManager = LinearLayoutManager(this)
        gamesRecyclerView.adapter = GameAdapter(gamesList, this)
    }

    override fun onItemClick(position: Int) {
        val game = gamesList[position]
        val intent = Intent(this, GameDetailsActivity::class.java).apply {
            putExtra("game_title", game.title)
        }
        startActivity(intent)

    }


}