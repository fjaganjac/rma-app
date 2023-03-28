package com.example.rma_spirala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private var gamesList = getGames()

    private lateinit var gamesRecyclerView: RecyclerView
    private lateinit var gamesRecyclerAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        gamesRecyclerView = findViewById(R.id.gameRecyclerView)


        gamesRecyclerView.layoutManager = LinearLayoutManager(this)
        gamesRecyclerView.adapter = GameAdapter(gamesList)
    }




}