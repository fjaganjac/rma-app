package com.example.rma_spirala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    /*private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/
    private lateinit var game: Game

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_game_details, container, false)


        titleView = view.findViewById(R.id.game_title_textview)
        coverView = view.findViewById(R.id.cover_imageview)
        platformView = view.findViewById(R.id.platform_textview)
        releaseDateView = view.findViewById(R.id.release_date_textview)
        esrbRatingView = view.findViewById(R.id.esrb_rating_textview)
        developerView = view.findViewById(R.id.developer_textview)
        publisherView = view.findViewById(R.id.publisher_textview)
        genreView = view.findViewById(R.id.genre_textview)
        descriptionView = view.findViewById(R.id.description_textview)
        userImpressionRecyclerView = view.findViewById(R.id.game_details_user_impression_recyclerview)

        userImpressionRecyclerView.layoutManager = LinearLayoutManager(activity)


        arguments?.getString("game_title")?.let {
            game=GameData.getDetails(it)

            titleView.text = game.title
            var id: Int = resources.getIdentifier(game.coverImage, "drawable","com.example.rma_spirala")
            coverView.setImageResource(id)
            platformView.text = game.platform
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
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}