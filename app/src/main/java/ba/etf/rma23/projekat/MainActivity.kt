package ba.etf.rma23.projekat


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.etf.rma23.projekat.data.repositories.GameReviewsRepository
import ba.etf.rma23.projekat.data.repositories.GamesRepository
import com.example.rma_spirala.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object {
        var prev = Game(-1, "", "", "", 0.0, "", "", "", "", "", "", listOf<UserImpression>())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    val homeFragment = HomeFragment()
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, homeFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.gameDetailsFragment -> {
                    val selectedGameBundle = Bundle()
                    selectedGameBundle.putSerializable("game", prev)
                    val destination = GameDetailsFragment()
                    destination.arguments = selectedGameBundle
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, destination).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.accountFragment -> {
                    val accountFragment = AccountFragment()
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, accountFragment).commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


        val grCrash = GameReview(0, 3,"nije losa iskreno", 1189, false)
        val grHitman = GameReview(0, 2,"dobraaaa", 11157, false)
        val grTetris2 = GameReview(0, 4,"sta je tetris2", 9083, false)
        val grHitman1 = GameReview(0, 2,"nije dobra nidje veze", 11157, false)

        //sendReview(this, grHitman1)

        //sendReviewsToService(this)
        //sendReviewToDB(this,grTetris2)
        //sendReviewToDB(this,grCrash)
        //sendReviewToDB(this,grHitman)
        //getFromDB(this)
        //sendReviewsToService(this)
        //nukeReviews(this)
        //testGetByID(11157)
        getReviewsById(this,11157)
    }

    private fun testGetByID(id: Int) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            // Make the network call and suspend execution until it finishes
            val result = GamesRepository.getGameByID(id)
            println("rezIgrice: $result");
        }
    }

    private fun getFromDB(context: Context) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            // Make the network call and suspend execution until it finishes
            val result = GameReviewsRepository.getOfflineReviews(context)
            println("lista: $result");
        }
    }

    private fun sendReview(context: Context, gameReview: GameReview) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            //val gr = GameReview(0, 2,"dobar", 1189, false)
            var b = GameReviewsRepository.sendReview(context, gameReview)
            println("idposlane: $b")
            //println("lista: asdasd");
        }
    }

    private fun sendReviewToDB(context: Context, gameReview: GameReview) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            //val gr = GameReview(0, 2,"dobar", 1189, false)
            GameReviewsRepository.sendReviewToLocalDB(context, gameReview)

        }
    }

    private fun nukeReviews(context: Context) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            GameReviewsRepository.deleteAll(context)
        }
    }

    private fun sendReviewsToService(context: Context) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            GameReviewsRepository.sendOfflineReviews(context)
        }
    }

    private fun getReviewsById(context: Context, igdb_id: Int) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            var l=GameReviewsRepository.getReviewsForGame(igdb_id)
            Log.i("test2",l.toString())
        }
    }

}