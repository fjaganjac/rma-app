package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*




data class DeleteResponse(
    @SerializedName("success") val success: String,
)

class AccountApiConfig {

    var age = 0;
    interface Api {

        @GET("games")
        suspend fun getSavedGames(    //dohvaca 10 igrica sa tim imenom
            //name: String,
            //@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
            /*@Header("Client-ID") clientId: String = "xgjrd2i4btzgwaxav98no3o6lhoqfk",
            @Header("Authorization") authorization: String = "Bearer mt6h9p07m6y0uwk7mtgogoglvwg5rh",*/
            //@Body body: RequestBody
        ): List<Game>

        @POST("game")
        @Headers(
            "Content-Type: application/json"
        )
        suspend fun saveGame(
            @Body body: RequestBody
        ):Game

        @DELETE("game")
        suspend fun DeleteGames(
        ):DeleteResponse


        @DELETE("game/{igdb_id}")
        suspend fun RemoveGame(
            @Path("igdb_id") igdbId: Int,
        ):DeleteResponse

    }

    object ApiAdapter {
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://rma23ws.onrender.com/account/${AccountGamesRepository.getHash()}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}