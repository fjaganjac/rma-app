package ba.etf.rma23.projekat

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Game (
    @SerializedName("igdb_id") val igdb_id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("coverImage") val coverImage: String,
    @SerializedName("esrbRating") val esrbRating: String,
    @SerializedName("developer") val developer: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("description") val description: String,
    @SerializedName("userImpressions") val userImpressions: List<UserImpression>, //ovo ce sad biti nista
) : Serializable
