package com.example.rma_spirala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private var titleList = mutableListOf<String>()
    private var platformList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    private lateinit var gamesRecyclerView: RecyclerView
    private lateinit var gamesRecyclerAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        postToList()

        gamesRecyclerView = findViewById(R.id.gameRecyclerView)


        gamesRecyclerView.layoutManager = LinearLayoutManager(this)
        gamesRecyclerView.adapter = GameAdapter(titleList,platformList, imageList)
    }

    private fun addToList(title:String, platform: String, image: Int) {
        titleList.add(title)
        platformList.add(platform)
        imageList.add(image)
    }

    private fun postToList() {
        for(i in 1..25) {
            addToList("Title $i", "Platform $i", R.mipmap.ic_launcher_round)
        }
    }
}