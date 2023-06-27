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
    }
}