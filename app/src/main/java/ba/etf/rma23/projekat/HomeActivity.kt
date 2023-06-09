package ba.etf.rma23.projekat



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.rma_spirala.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    companion object {
        var prev = Game(-1,"","","",0.0,"","","","","","",listOf<UserImpression>())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.setupWithNavController(navController)

        //println("aSDASDASD")

        /*val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            AccountGameRepository.setAge(3)
            println("GOD "+AccountGameRepository.age)
            var lista = GamesRepository.getGamesSafe("Valorant")
            println("GOD "+lista)

        }*/

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    //navView.selectedItemId= R.id.homeFragment
                    val homeFragment = HomeFragment()
                    this.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,homeFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.gameDetailsFragment -> {
                    val selectedGameBundle = Bundle()
                    selectedGameBundle.putSerializable("game", prev)
                    val destination = GameDetailsFragment()
                    destination.arguments = selectedGameBundle
                    this.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,destination).commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }



}