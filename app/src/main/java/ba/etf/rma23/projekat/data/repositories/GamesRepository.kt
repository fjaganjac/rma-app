package ba.etf.rma23.projekat.data.repositories


import ba.etf.rma23.projekat.Game
import ba.etf.rma23.projekat.UserImpression
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import kotlin.math.roundToInt

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

object GamesRepository {

    private const val tmdb_api_key: String = "YOUR KEY HERE"
    var client_id = "xgjrd2i4btzgwaxav98no3o6lhoqfk"
    var clientSecret = "3w8ptmoas23o6kkiamsiom40yoh7yk"

    var GamesList = listOf<Game>()

    suspend fun getGameByID(igdb_id: Int): Game {
        return withContext(Dispatchers.IO) {
            var body =
                "fields name, rating, platforms.name, release_dates.human, cover.url, involved_companies.company.name, genres.name, summary, age_ratings.category, age_ratings.rating;\n" +
                        "where id = $igdb_id;"
            var reqbody = RequestBody.create(MediaType.parse("text/plain"), body)
            try {
                var response = IGDBApiConfig.ApiAdapter.retrofit.getGamesByName(reqbody)

                var igrica =
                    Game(-1, "", "", "", 0.0, "", "", "", "", "", "", listOf<UserImpression>())
                if (response.code() == 200) {
                    var resbody = response.body()?.get(0)
                    igrica = resbody?.let {
                        var ratingStr = "Unrated"
                        if (resbody.age_ratings?.get(0)?.category == 1) {   //1 -> ESRB
                            when (resbody.age_ratings!![0].rating) {
                                6 -> ratingStr = "RP"
                                7 -> ratingStr = "EC"
                                8 -> ratingStr = "E"
                                9 -> ratingStr = "E10"
                                10 -> ratingStr = "T"
                                11 -> ratingStr = "M"
                                12 -> ratingStr = "AO"
                            }
                        } else if (resbody.age_ratings?.get(0)?.category == 2) {   //2 -> PEGI
                            when (resbody.age_ratings!![0].rating) {
                                1 -> ratingStr = "PEGI 3"
                                2 -> ratingStr = "PEGI 7"
                                3 -> ratingStr = "PEGI 12"
                                4 -> ratingStr = "PEGI 16"
                                5 -> ratingStr = "PEGI 18"
                            }
                        }
                        Game(
                            it.id,
                            resbody.name,
                            resbody.platforms[0].toString(),
                            resbody.release_dates[0].toString(),
                            resbody.rating,
                            resbody.cover.url,
                            ratingStr,
                            resbody.companies[0].company.name,
                            resbody.companies[0].company.name,
                            resbody.genres[0].name,
                            resbody.summary,
                            listOf<UserImpression>()
                        )
                    }!!
                }
                return@withContext igrica
            } catch (e: Exception) {
                return@withContext Game(
                    -1,
                    "",
                    "",
                    "",
                    0.0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    listOf<UserImpression>()
                )
            }
        }


    }

    suspend fun getGamesByName(
        name: String
    ): List<Game> {
        return withContext(Dispatchers.IO) {
            var fields =
                "fields name, rating, platforms.name, release_dates.human, cover.url, involved_companies.company.name, genres.name, summary, age_ratings.category, age_ratings.rating;\n" +
                        "search \"$name\";\n" +
                        "where rating != null;\n" +
                        "limit 10;"
            //println("FIELDS " + fields)
            val requestBody = RequestBody.create(MediaType.parse("text/plain"), fields)
            try {
                var response = IGDBApiConfig.ApiAdapter.retrofit.getGamesByName(requestBody)

                val lista: MutableList<Game> = ArrayList()
                var resBody = response.body()
                if (resBody != null) {
                    for (item in resBody) {
                        var ratingStr = "Unrated"
                        if (item.age_ratings?.get(0)?.category == 1) {   //1 -> ESRB
                            when (item.age_ratings[0].rating) {
                                6 -> ratingStr = "RP"
                                7 -> ratingStr = "EC"
                                8 -> ratingStr = "E"
                                9 -> ratingStr = "E10"
                                10 -> ratingStr = "T"
                                11 -> ratingStr = "M"
                                12 -> ratingStr = "AO"
                            }
                        } else if (item.age_ratings?.get(0)?.category == 2) {   //2 -> PEGI
                            when (item.age_ratings[0].rating) {
                                1 -> ratingStr = "PEGI 3"
                                2 -> ratingStr = "PEGI 7"
                                3 -> ratingStr = "PEGI 12"
                                4 -> ratingStr = "PEGI 16"
                                5 -> ratingStr = "PEGI 18"
                            }
                        }
                        var title = item.name

                        var datum = ""
                        if (item.release_dates != null) {
                            datum = item.release_dates[0].human
                        }

                        var platforme = item.platforms
                        var platforme2 = ""
                        if (platforme != null) {
                            for (platforma in platforme) {
                                platforme2 += platforma.name + "/"
                            }
                        }
                        platforme2 = platforme2.dropLast(1)

                        var ratingMoj = 0.0
                        if (item.rating != null) {
                            ratingMoj = item.rating
                        }
                        val roundoff = (ratingMoj * 10.0).roundToInt() / 10.0


                        var coverUrl = ""    //provjera na null
                        if (item.cover != null) {
                            coverUrl = item.cover.url
                        }


                        var dev = "Unknown"         //null check
                        if (item.companies != null) {
                            dev = item.companies[0]?.company?.name
                        }
                        var pub = dev

                        var zanr = ""
                        if (item.genres != null) {
                            zanr = item.genres[0].name
                        }

                        var desc = ""
                        if (item.summary != null) {
                            desc = item.summary
                        }

                        val impresioni: MutableList<UserImpression> = ArrayList()
                        var id = item.id

                        var igra = Game(
                            id,
                            title,
                            platforme2,
                            datum,
                            roundoff,
                            coverUrl,
                            ratingStr,
                            dev,
                            pub,
                            zanr,
                            desc,
                            impresioni
                        )
                        //println(igra.toString())
                        lista.add(igra)
                    }
                    GamesList = lista
                }

                return@withContext lista
            } catch (e: Exception) {
                return@withContext listOf<Game>()
            }
        }
    }

    suspend fun getGamesSafe(
        name: String
    ): List<Game> {
        return withContext(Dispatchers.IO) {
            var fields =
                "fields name, rating, platforms.name, release_dates.human, cover.url, involved_companies.company.name, genres.name, summary, age_ratings.category, age_ratings.rating;\n" +
                        "search \"$name\";\n" +
                        "where rating != null;\n" +
                        "limit 10;"
            //println("FIELDS " + fields)
            val requestBody = RequestBody.create(MediaType.parse("text/plain"), fields)
            var response = IGDBApiConfig.ApiAdapter.retrofit.getGamesByName(requestBody)

            val lista: MutableList<Game> = ArrayList()
            var resBody = response.body()
            if (resBody != null) {
                for (item in resBody) {
                    var ratingStr = "Unrated"
                    var godineInt = 0;
                    if (item.age_ratings?.get(0)?.category == 1) {   //1 -> ESRB
                        when (item.age_ratings[0].rating) {
                            6 -> {
                                ratingStr = "RP"
                                godineInt = 99
                            }
                            7 -> {
                                ratingStr = "EC"
                                godineInt = 3
                            }
                            8 -> {
                                ratingStr = "E"
                                godineInt = 3
                            }
                            9 -> {
                                ratingStr = "E10"
                                godineInt = 10
                            }
                            10 -> {
                                ratingStr = "T"
                                godineInt = 13
                            }
                            11 -> {
                                ratingStr = "M"
                                godineInt = 17
                            }
                            12 -> {
                                ratingStr = "AO"
                                godineInt = 18
                            }
                        }
                    } else if (item.age_ratings?.get(0)?.category == 2) {   //2 -> PEGI
                        when (item.age_ratings[0].rating) {
                            1 -> {
                                ratingStr = "PEGI 3"
                                godineInt = 3
                            }
                            2 -> {
                                ratingStr = "PEGI 7"
                                godineInt = 7
                            }
                            3 -> {
                                ratingStr = "PEGI 12"
                                godineInt = 12
                            }
                            4 -> {
                                ratingStr = "PEGI 16"
                                godineInt = 16
                            }
                            5 -> {
                                ratingStr = "PEGI 18"
                                godineInt = 18
                            }
                        }
                    }
                    var title = item.name

                    var datum = ""
                    if (item.release_dates != null) {
                        datum = item.release_dates[0].human
                    }

                    var platforme = item.platforms
                    var platforme2 = ""
                    if (platforme != null) {
                        for (platforma in platforme) {
                            platforme2 += platforma.name + "/"
                        }
                    }
                    platforme2 = platforme2.dropLast(1)

                    var ratingMoj = 0.0
                    if (item.rating != null) {
                        ratingMoj = item.rating
                    }
                    val roundoff = (ratingMoj * 10.0).roundToInt() / 10.0


                    var coverUrl = ""    //provjera na null
                    if (item.cover != null) {
                        coverUrl = item.cover.url
                    }


                    var dev = "Unknown"         //null check
                    if (item.companies != null) {
                        dev = item.companies[0]?.company?.name
                    }
                    var pub = dev

                    var zanr = ""
                    if (item.genres != null) {
                        zanr = item.genres[0].name
                    }

                    var desc = ""
                    if (item.summary != null) {
                        desc = item.summary
                    }

                    val impresioni: MutableList<UserImpression> = ArrayList()
                    var id = item.id

                    var igra = Game(
                        id,
                        title,
                        platforme2,
                        datum,
                        roundoff,
                        coverUrl,
                        ratingStr,
                        dev,
                        pub,
                        zanr,
                        desc,
                        impresioni
                    )

                    //println("GODINE TRENUTNO "+ AccountGameRepository.age)
                    if (godineInt < AccountGamesRepository.age) {
                        lista.add(igra)
                    }

                }
            }
            GamesList = lista
            return@withContext lista
        }
    }

    suspend fun sortGames(): List<Game> {
        return withContext(Dispatchers.IO) {
            //GamesList.sortedBy{it.title}
            //napravi pomocnu listu
            //u nju stavi sve koje su omiljene
            //sortiraj pomocnu listu po imenu
            //sortiraj tekucu listu po imenu
            //spoji dvije liste
            var kopija = GamesList
            var savedIds = AccountGamesRepository.getSavedGames().map { it.id }
            GamesList = kopija
            val pomocna: MutableList<Game> = ArrayList()
            for (item in GamesList) {
                if (savedIds.contains(item.id)) {
                    pomocna.add(item)
                }
            }
            pomocna.sortBy { it.title }

            GamesList = GamesList.sortedBy { it.title }

            for (item in GamesList) {
                if (!savedIds.contains(item.id))
                    pomocna.add(item)
            }
            println("POMOCNA " + pomocna)
            GamesList = pomocna
            return@withContext GamesList
        }

    }
}