package ba.etf.rma23.projekat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma23.projekat.data.repositories.AccountGamesRepository
import ba.etf.rma23.projekat.data.repositories.GameReviewsRepository
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import com.example.rma_spirala.R
import com.google.android.material.slider.Slider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var revs = listOf<GameReview>()

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
    private lateinit var saveButtonView: Button
    private lateinit var removeButtonView: Button
    private lateinit var reviewReviewView: EditText
    private lateinit var reviewRatingView: Slider
    private lateinit var sendReviewButton: Button

    private var ReviewsAdapter = GameReviewAdapter(revs)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_game_details, container, false)
        titleView = view.findViewById(R.id.item_title_textview)
        coverView = view.findViewById(R.id.cover_imageview)
        platformView = view.findViewById(R.id.platform_textview)
        releaseDateView = view.findViewById(R.id.release_date_textview)
        esrbRatingView = view.findViewById(R.id.esrb_rating_textview)
        developerView = view.findViewById(R.id.developer_textview)
        publisherView = view.findViewById(R.id.publisher_textview)
        genreView = view.findViewById(R.id.genre_textview)
        descriptionView = view.findViewById(R.id.description_textview)
        userImpressionRecyclerView =
            view.findViewById(R.id.game_details_user_impression_recyclerview)
        userImpressionRecyclerView.layoutManager = LinearLayoutManager(activity)


        saveButtonView = view.findViewById(R.id.save_button)
        saveButtonView.setOnClickListener { saveGame() }

        removeButtonView = view.findViewById(R.id.remove_button)
        removeButtonView.setOnClickListener { removeGame() }

        reviewReviewView = view.findViewById(R.id.ReviewReviewEditText)
        reviewRatingView = view.findViewById(R.id.ReviewRatingSlider)
        sendReviewButton = view.findViewById(R.id.sendReviewButton)
        sendReviewButton.setOnClickListener { reviewSend() }



        arguments?.getSerializable("game")?.let {
            game = it as Game
            titleView.text = game.title
            Picasso.get().load(game.coverImage).into(coverView)
            platformView.text = game.platform
            releaseDateView.text = game.releaseDate
            platformView.text = game.platform
            releaseDateView.text = game.releaseDate
            esrbRatingView.text = game.esrbRating
            developerView.text = game.developer
            publisherView.text = game.publisher
            genreView.text = game.genre
            descriptionView.text = game.description
        }

        var result: List<GameReview> = listOf<GameReview>()
        val scope1 = CoroutineScope(Job() + Dispatchers.Main)

        scope1.launch {
            result = GameReviewsRepository.getReviewsForGame(game.id)
            revs = result
            ReviewsAdapter.updateReviews(result)
        }
        userImpressionRecyclerView.layoutManager = LinearLayoutManager(activity)
        userImpressionRecyclerView.adapter = ReviewsAdapter
        if (revs.isEmpty())
            userImpressionRecyclerView.layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
        return view
    }

    private fun saveGame() {
        val toast = Toast.makeText(context, "Game Added", Toast.LENGTH_SHORT)
        toast.show()
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            AccountGamesRepository.saveGame(game)
        }
    }

    private fun removeGame() {
        val toast = Toast.makeText(context, "Game Removed", Toast.LENGTH_SHORT)
        toast.show()
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            AccountGamesRepository.removeGame(game.id)
        }

    }

    private fun reviewSend() {
        val gam = GameReview(
            reviewRatingView.value.toInt(),
            reviewReviewView.text.toString(),
            game.id,
            false,
            "",
            ""
        )
        CoroutineScope(Job() + Dispatchers.IO).launch {
            context?.let { GameReviewsRepository.sendReview(it, gam) }
        }
        reviewReviewView.setText("")
        reviewRatingView.value = 0F
        val toast = Toast.makeText(context, "Review Submitted", Toast.LENGTH_SHORT)
        toast.show()


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