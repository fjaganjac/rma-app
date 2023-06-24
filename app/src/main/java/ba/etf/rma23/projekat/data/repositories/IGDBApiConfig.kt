package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.example.rma_spirala.BuildConfig
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*


/*data class GetGamesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Game>,
    @SerializedName("total_pages") val pages: Int
)*/


//za idneks testiras sa isEmpty()

data class Platform(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name: String
    )

data class ReleaseDates(
    @SerializedName("id") val id : Int,
    @SerializedName("human") val human: String
)

data class Cover(
    @SerializedName("id") val id : Int,
    @SerializedName("url") val url: String
)

data class Company(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name: String
)

data class InvolvedCompany(
    @SerializedName("id") val id : Int,
    @SerializedName("company") val company: Company
)

data class Genre(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name: String
)

data class AgeRating(
    @SerializedName("id") val id : Int,
    @SerializedName("category") val category: Int,
    @SerializedName("rating") val rating: Int,
)

data class GameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("platforms") val platforms: List<Platform>,
    @SerializedName("release_dates") val release_dates: List<ReleaseDates>,
    @SerializedName("cover") val cover : Cover,
    @SerializedName("involved_companies") val companies : List<InvolvedCompany>,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("summary") val summary: String,
    @SerializedName("age_ratings") val age_ratings: List<AgeRating>?,

)

class IGDBApiConfig {
    interface Api {
        @POST("games")
        @Headers (
            "Client-ID: xgjrd2i4btzgwaxav98no3o6lhoqfk",
            "Authorization: Bearer mt6h9p07m6y0uwk7mtgogoglvwg5rh"
        )
        suspend fun getGamesByName(
            @Body body: RequestBody

        ): Response<List<GameResponse>>

        @POST("games")
        @Headers (
            "Client-ID: xgjrd2i4btzgwaxav98no3o6lhoqfk",
            "Authorization: Bearer mt6h9p07m6y0uwk7mtgogoglvwg5rh"
        )
        suspend fun getGamesSafe(
            //name: String,
            @Body body: RequestBody
        ): Response<List<GameResponse>>
    }

    object ApiAdapter {
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://api.igdb.com/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}