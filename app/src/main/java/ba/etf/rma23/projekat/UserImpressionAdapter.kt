package ba.etf.rma23.projekat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_spirala.R

class UserImpressionAdapter(
    private var userImpressions: List<UserImpression>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder1(itemView: View):RecyclerView.ViewHolder(itemView) {
        var itemUsername : TextView = itemView.findViewById(R.id.username_textview)
        var itemRating : RatingBar = itemView.findViewById(R.id.rating_bar)
        //val
    }

    inner class ViewHolder2(itemView: View):RecyclerView.ViewHolder(itemView) {
        var itemUsername : TextView = itemView.findViewById(R.id.username_textview)
        var itemReview : TextView = itemView.findViewById(R.id.review_textview)
    }

    private var rating = 0;
    private var review = 1;
    override fun getItemViewType(position: Int): Int {
        if (userImpressions[position] is UserRating) {
            return rating
        }
        else if(userImpressions[position] is UserReview) {
            return review
        }
        return -1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val viewHolder: RecyclerView.ViewHolder
    val inflater = LayoutInflater.from(parent.context)
        viewHolder = when (viewType) {
            rating -> {
                val v1: View = inflater.inflate(R.layout.item_user_rating,parent,false)
                ViewHolder1(v1)
            }
            review -> {
                val v2: View = inflater.inflate(R.layout.item_user_review,parent,false)
                ViewHolder2(v2)
            }
            else -> {   //ne bi trebao uci
                val v: View = inflater.inflate(R.layout.item_user_rating, parent, false)
                ViewHolder1(v)
            }
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            rating -> {
                var vh1 = holder as ViewHolder1
                configureViewHolder1(vh1,position)
            }
            review -> {
                var vh2 = holder as ViewHolder2
                configureViewHolder2(vh2,position)
            }
        }
    }

    private fun configureViewHolder1(vh1: ViewHolder1, position: Int) {
        val rating = userImpressions[position] as UserRating
        if (rating != null) {
            vh1.itemUsername.text = rating.username
            vh1.itemRating.rating = rating.rating.toFloat()
        }
    }

    private fun configureViewHolder2(vh2: ViewHolder2, position: Int) {
        val review = userImpressions[position] as UserReview
        if(review != null) {
            vh2.itemUsername.text = review.username
            vh2.itemReview.text = review.review
        }
    }

    override fun getItemCount(): Int {
        return  userImpressions.size
    }
}