package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma23.projekat.GameReview
import ba.etf.rma23.projekat.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import java.lang.Exception

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
            println("a4_: broj offline" + reviews.size)
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
            for (it in accountRepoRes) {
                if (it.id == gameReview.igdb_id) {
                    imaSpasena = true
                    break
                }
            }
            if (!imaSpasena) {
                val game = GamesRepository.getGameByID(gameReview.igdb_id)
                AccountGamesRepository.saveGame(game)
            }
            val body = "{\n" +
                    "  \"review\": \"${gameReview.review}\",\n" +
                    "  \"rating\": ${gameReview.rating}\n" +
                    "}"
            val reqbody = RequestBody.create(MediaType.parse("text/plain"), body)
            try {
                val response = GameReviewsApiConfig.ApiAdapter.retrofit.sendReview(
                    "d8da4bfc-fc15-4463-ae5a-6dc2c731366a",
                    gameReview.igdb_id,
                    reqbody
                )
                var boolResponse: Boolean
                if (response.code() != 200) {
                    var db = AppDatabase.getInstance(context)
                    db!!.GRDAO().insertAll(gameReview)
                    println("a4_:" + "poslao sam" +gameReview)
                    boolResponse = false
                } else {
                    boolResponse = true
                }
                return@withContext boolResponse

            } catch (e: Exception) {
                var db = AppDatabase.getInstance(context)
                db!!.GRDAO().insertAll(gameReview)
                println("a4_:" + "poslao sam" + gameReview)
                return@withContext false
            }

        }
    }

    suspend fun getReviewsForGame(igdb_id: Int): List<GameReview> {
        return withContext(Dispatchers.IO) {
            try {
                println("a5_: prosljedjeni int" + igdb_id)
                val response = GameReviewsApiConfig.ApiAdapter.retrofit.getReviews(igdb_id)
                println("a5_: ispis ispod poziva")
                val listaKomentara = response.body()
                val rez: MutableList<GameReview> = mutableListOf()

                if (listaKomentara != null) {
                    for (it in listaKomentara) {
                        rez.add(
                            GameReview(
                                0,
                                it.rating,
                                it.review,
                                it.id,
                                true,
                                it.student,
                                it.timestamp
                            )
                        )
                    }
                }
                println("a5_: lista komentara" + rez)
                return@withContext rez
            } catch (e: Exception) {
                println("a5_: izuzetakk" + e.message)

                return@withContext listOf<GameReview>()
            }

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