package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma23.projekat.GameReview
import ba.etf.rma23.projekat.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody

object GameReviewsRepository {


    suspend fun getOfflineReviews(context: Context): List<GameReview> {
        return withContext(Dispatchers.IO) {
            var db = AppDatabase.getInstance(context)
            var reviews = db!!.GRDAO().getOffline()
            return@withContext reviews
        }
    }

    suspend fun sendOfflineReviews(context: Context): Int {
        return withContext(Dispatchers.IO) {
            var db = AppDatabase.getInstance(context)
            var reviews = db!!.GRDAO().getOffline()
            for (item in reviews) {
                sendReview(context, item)
            }
            db.GRDAO().setOnlineToTrue()
            return@withContext reviews.size
        }
    }

    suspend fun sendReview(
        context: Context,
        gameReview: GameReview
    ): Boolean {
        return withContext(Dispatchers.IO) {
            var accountRepoRes = AccountGamesRepository.getSavedGames()
            var imaSpasena = false
            for(it in accountRepoRes) {
                if(it.id == gameReview.igdb_id) {
                    imaSpasena = true
                    break
                }
            }
            if(!imaSpasena) {
                val game = GamesRepository.getGameByID(gameReview.igdb_id)
                AccountGamesRepository.saveGame(game)
            }
            val body = "{\n" +
                    "  \"review\": \"${gameReview.review}\",\n" +
                    "  \"rating\": ${gameReview.rating}\n" +
                    "}"
            val reqbody = RequestBody.create(MediaType.parse("text/plain"), body)
            val response = GameReviewsApiConfig.ApiAdapter.retrofit.sendReview("d8da4bfc-fc15-4463-ae5a-6dc2c731366a", gameReview.igdb_id, reqbody)
            var boolResponse: Boolean
            if (response.code() != 200) {
                var db = AppDatabase.getInstance(context)
                db!!.GRDAO().insertAll(gameReview)
                boolResponse = false
            } else {
                boolResponse = true
            }
            return@withContext boolResponse
        }
    }

    suspend fun getReviewsForGame(igdb_id: Int): List<GameReviewResponse>? {
        return withContext(Dispatchers.IO) {
            val response = GameReviewsApiConfig.ApiAdapter.retrofit.getReviews(igdb_id.toString())

            val listaKomentara = response.body()
            return@withContext listaKomentara
        }
    }

    suspend fun sendReviewToLocalDB(  //ova nema u spirali koristim za testiranje baze
        context: Context,
        gameReview: GameReview
    ): String {
        return withContext(Dispatchers.IO) {

            var db = AppDatabase.getInstance(context)
            db!!.GRDAO().insertAll(gameReview)

            return@withContext "poslao"
        }
    }

    suspend fun deleteAll(context: Context): String {   //nema ni ovo koristi za brisanje iz lokalne baze
        return withContext(Dispatchers.IO) {
            var db = AppDatabase.getInstance(context)
            db!!.GRDAO().deleteAll()
            return@withContext "reviews"
        }
    }

}