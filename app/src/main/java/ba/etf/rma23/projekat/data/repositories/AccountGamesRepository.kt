package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response


object AccountGamesRepository {
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
            val lista :MutableList<Game> = ArrayList()
            for(item in response) {
                lista.add(GamesRepository.getGamesByName(item.title)[0])
            }
            println("Res RES RES $lista")
            return@withContext lista
        }
    }


    suspend fun saveGame(game:Game):Game {
        return withContext(Dispatchers.IO) {

            var fields = "{\n" +
                    "  \"game\": {\n" +
                    "    \"igdb_id\": ${game.id},\n" +
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

            var response = GamesRepository.GamesList
            val lista :MutableList<Game> = ArrayList()
            for(item in response) {
                var ageRating = 0
                if (item.esrbRating!=null) {
                    when(item.esrbRating) {
                        "RP"-> ageRating = 99
                        "EC" ->ageRating = 3
                        "E" -> ageRating = 3
                        "E10" -> ageRating = 10
                        "T" -> ageRating = 13
                        "M" -> ageRating = 17
                        "A0" -> ageRating = 18
                        "PEGI 3" -> ageRating = 3
                        "PEGI 7" -> ageRating = 7
                        "PEGI 12" -> ageRating = 12
                        "PEGI 16" -> ageRating = 16
                        "PEGI 18" -> ageRating = 18
                    }
                    if(age >= ageRating) {
                        lista.add(GamesRepository.getGamesByName(item.title)[0])
                    }
                    else {
                        removeGame(item.id)
                    }
                }

                GamesRepository.GamesList = lista
            }
            return@withContext true
        }
    }

    suspend fun getGamesContainingString(query:String): List<Game> {
        return withContext(Dispatchers.IO) {
            var response = GamesRepository.GamesList
            println("REZ "+response)
            val lista :MutableList<Game> = ArrayList()
            for(item in response) {
                if(item.title.contains(query)) {
                    lista.add(GamesRepository.getGamesByName(item.title)[0])
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