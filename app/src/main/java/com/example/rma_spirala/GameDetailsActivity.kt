package com.example.rma_spirala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameDetailsActivity : AppCompatActivity() {
    private lateinit var titleView: TextView
    private lateinit var releaseDateView: TextView
    private lateinit var ratingView: TextView
    private lateinit var platformView: TextView

    private lateinit var game: Game


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)


        titleView = findViewById(R.id.game_details_title_textview)
        releaseDateView = findViewById(R.id.game_details_release_date_textview)
        ratingView = findViewById(R.id.game_details_rating_textview)
        platformView = findViewById(R.id.game_details_platform_textview)

        game = getGameByTitle(intent.getStringExtra("game_title").toString())

        titleView.text = game.title
        releaseDateView.text = game.releaseDate
        ratingView.text = game.rating.toString()
        platformView.text = game.platform



    }

    private fun getGameByTitle(title: String): Game {
        val games = getGames()
        val game = games.find { game -> title == game.title }
        return game?:Game("Test","Test","Test",0.0)
    }
}