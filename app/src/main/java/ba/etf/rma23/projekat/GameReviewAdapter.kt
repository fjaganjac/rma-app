package ba.etf.rma23.projekat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_spirala.R

class GameReviewAdapter(private var Reviews: List<GameReview>) :
    RecyclerView.Adapter<GameReviewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemUsername: TextView = itemView.findViewById(R.id.game_review_username_textview)
        var itemReview: TextView = itemView.findViewById(R.id.game_review_review_textview)
        var itemRating: RatingBar = itemView.findViewById(R.id.game_review_rating_bar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_game_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemUsername.text = Reviews[position].student
        holder.itemReview.text = Reviews[position].review
        holder.itemRating.rating = Reviews[position].rating!!.toFloat()

        if (Reviews[position].rating == 0) {
            holder.itemRating.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return Reviews.size
    }

    fun updateReviews(reviews: List<GameReview>?) {
        if (reviews != null) {
            this.Reviews = reviews
        }
        notifyDataSetChanged()
    }
}