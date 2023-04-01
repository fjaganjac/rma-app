package com.example.rma_spirala

import android.app.ActionBar
import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameDetailsActivity : AppCompatActivity() {

    private lateinit var homeButton: Button

    private lateinit var titleView: TextView
    private lateinit var releaseDateView: TextView
    private lateinit var platformView: TextView
    private lateinit var coverView: ImageView
    private lateinit var esrbRatingView: TextView
    private lateinit var developerView: TextView
    private lateinit var publisherView: TextView
    private lateinit var genreView: TextView
    private lateinit var descriptionView: TextView
    private lateinit var userImpressionRecyclerView: RecyclerView


    private lateinit var game: Game


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        homeButton = findViewById(R.id.home_button)
        titleView = findViewById(R.id.game_title_textview)
        coverView = findViewById(R.id.cover_imageview)
        platformView = findViewById(R.id.platform_textview)
        releaseDateView = findViewById(R.id.release_date_textview)
        esrbRatingView = findViewById(R.id.esrb_rating_textview)
        developerView = findViewById(R.id.developer_textview)
        publisherView = findViewById(R.id.publisher_textview)
        genreView = findViewById(R.id.genre_textview)
        descriptionView = findViewById(R.id.description_textview)
        userImpressionRecyclerView = findViewById(R.id.game_details_user_impression_recyclerview)

        userImpressionRecyclerView.layoutManager = LinearLayoutManager(this)

        homeButton.setOnClickListener { finish(); }



        game = GameData.getDetails(intent.getStringExtra("game_title").toString())
        val context: Context = coverView.context
        var id: Int = context.resources.getIdentifier(game.coverImage, "drawable", packageName)
        coverView.setImageResource(id)
        titleView.text = game.title
        releaseDateView.text = game.releaseDate
        platformView.text = game.platform
        releaseDateView.text = game.releaseDate
        esrbRatingView.text = game.esrbRating
        developerView.text = game.developer
        publisherView.text = game.publisher
        genreView.text = game.genre
        descriptionView.text = game.description
        userImpressionRecyclerView.adapter = UserImpressionAdapter(game.userImpressions)

        if (game.userImpressions.isEmpty())
            userImpressionRecyclerView.layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
    }

}