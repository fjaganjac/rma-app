package com.example.rma_spirala


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {
    companion object {
        var prev = ""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    //navView.selectedItemId= R.id.homeFragment
                    val homeFragment = HomeFragment()
                    this.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,homeFragment).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.gameDetailsFragment -> {
                    //navView.selectedItemId= R.id.gameDetailsFragment


                    val selectedGameBundle = Bundle()
                    selectedGameBundle.putString("game_title", prev)
                    val destination = GameDetailsFragment()
                    destination.arguments = selectedGameBundle

                    this.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,destination).commit()



                    return@setOnItemSelectedListener true
                }

            }
            false
        }

        /*if(intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain")
        {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                val bundle = bundleOf("game_details" to it)
                navView.selectedItemId= R.id.gameDetailsFragment
                navController.navigate(R.id.gameDetailsFragment,bundle)
            }
        }*/

    }



}