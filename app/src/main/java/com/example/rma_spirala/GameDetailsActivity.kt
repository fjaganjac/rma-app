package com.example.rma_spirala

import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class GameDetailsActivity : AppCompatActivity() {
    private lateinit var titleView: TextView
    private lateinit var releaseDateView: TextView

    private lateinit var platformView: TextView
    private lateinit var coverView: ImageView
    private lateinit var esrbRatingView: TextView
    private lateinit var developerView: TextView
    private lateinit var publisherView: TextView
    private lateinit var genreView: TextView
    private lateinit var descriptionView: TextView


    private lateinit var game: Game


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)


        titleView = findViewById(R.id.game_details_title_textview)
        coverView = findViewById(R.id.cover_imageview)
        platformView = findViewById(R.id.game_details_platform_textview)
        releaseDateView = findViewById(R.id.game_details_release_date_textview)
        esrbRatingView = findViewById(R.id.game_details_esrb_rating_textview)
        developerView = findViewById(R.id.game_details_developer_textview)
        publisherView = findViewById(R.id.game_details_publisher_textview)
        genreView = findViewById(R.id.game_details_genre_textview)
        descriptionView = findViewById(R.id.game_details_description_textview)


        game = getGameByTitle(intent.getStringExtra("game_title").toString())
        val context: Context = coverView.context
        var id: Int = context.resources.getIdentifier(game.coverImage,"drawable", packageName)
        coverView.setImageResource(id)
        titleView.text = game.title
        releaseDateView.text = game.releaseDate
        platformView.text = game.platform
        releaseDateView.text =game.releaseDate
        esrbRatingView.text = game.esrbRating
        developerView.text = game.developer
        publisherView.text = game.publisher
        genreView.text = game.genre
        descriptionView.text = game.description



    }

    private fun getGameByTitle(title: String): Game {
        val games = getGames()
        val game = games.find { game -> title == game.title }
        return game?:Game(
            "Test",
            "Test",
            "Test",
            0.0,
            "Test",
            "Test",
            "Test",
            "Test",
            "Test",
            "Test",
            listOf(UserReview("Test",0,"Test")))
    }
}