package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

data class GameReviewResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("rating") val rating: Int,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("review") val review: String?,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("updatedAt") val updatedAt : String,
    @SerializedName("student") val student: String,
    @SerializedName("GameId") val GameId: Int,
    @SerializedName("Message") val Message: String?,
    )

class GameReviewsApiConfig {
    //var hash = AccountGamesRepository.getHash()

    interface Api {
        @POST("account/{user_id}/game/{game_id}/gamereview")
        @Headers(
            "Content-Type: application/json"
        )
        suspend fun sendReview(
            @Path("user_id") user_id: String,
            @Path("game_id") game_id: Int,
            @Body body: RequestBody,
            ): Response<GameReviewResponse>


        @GET("game/{game_id}/gamereviews")
        suspend fun getReviews(
            @Path("game_id") game_id: String
        ): Response<List<GameReviewResponse>>

    }

    object ApiAdapter {
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://rma23ws.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}