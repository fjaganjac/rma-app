package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response


object AccountGameRepository {
    var hash : String = "d8da4bfc-fc15-4463-ae5a-6dc2c731366a"
    var age = 1

    fun setHash(
        acHash:String
    ):Boolean {
        this.hash = acHash
        return true;
    }

    @JvmName("getHash1")
    fun getHash():String {
        return hash;
    }

    suspend fun getSavedGames(): List<Game> {
        return withContext(Dispatchers.IO) {

            var response = AccountApiConfig.ApiAdapter.retrofit.getSavedGames()
            return@withContext response
        }
    }


    suspend fun saveGame(game:Game):Game {
        return withContext(Dispatchers.IO) {

            var fields = "{\n" +
                    "  \"game\": {\n" +
                    "    \"igdb_id\": ${game.igdb_id},\n" +
                    "    \"name\": \"${game.title}\"\n" +
                    "  }\n" +
                    "}"
            val requestBody = RequestBody.create(MediaType.parse("text/plain"),fields)
            AccountApiConfig.ApiAdapter.retrofit.saveGame(requestBody)

            return@withContext game
        }
    }

    suspend fun removeGame(id:Int):Boolean {
        return withContext(Dispatchers.IO) {
            //println("PROSLOOOO")
            AccountApiConfig.ApiAdapter.retrofit.RemoveGame(id)
            return@withContext true
        }
    }

    suspend fun removeNonSafe(): Boolean {
        return withContext(Dispatchers.IO) {



            return@withContext true
        }
    }

    suspend fun getGamesContainingString(query:String): List<Game> {
        return withContext(Dispatchers.IO) {
            var response = AccountApiConfig.ApiAdapter.retrofit.getSavedGames()
            val lista :MutableList<Game> = ArrayList()
            for(item in response) {
                if(item.title.contains(query)) {
                    lista.add(item)
                }
            }
            return@withContext lista
        }
    }

    fun setAge(age:Int):Boolean {
        this.age = age
        return true
    }



}