package com.example.rma_spirala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity(), GameAdapter.RecyclerViewEvent {

    private var gamesList = GameData.getAll()

    private lateinit var gamesRecyclerView: RecyclerView

    private lateinit var detailsButton: Button

    private var previousGame = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        gamesRecyclerView = findViewById(R.id.game_list)

        detailsButton = findViewById(R.id.details_button)
        detailsButton.setOnClickListener { previousGameDetails() }

        gamesRecyclerView.layoutManager = LinearLayoutManager(this)
        gamesRecyclerView.adapter = GameAdapter(gamesList, this)
    }

    override fun onItemClick(position: Int) {

        val game = gamesList[position]
        previousGame = gamesList[position].title
        val intent = Intent(this, GameDetailsActivity::class.java).apply {
            putExtra("game_title", game.title)
        }
        startActivity(intent)

    }

    private fun previousGameDetails() {
        if (previousGame != "") {
            val intent = Intent(this, GameDetailsActivity::class.java).apply {
                putExtra("game_title", previousGame)
            }
            startActivity(intent)

        }
    }


}