package com.erhansen.popularmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.erhansen.popularmovies.R
import com.erhansen.popularmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(HomeFragment())

        with(activityMainBinding) {
            bottomNavigationView.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.buttonHomePage -> {
                        replaceFragment(HomeFragment())
                    }
                    R.id.buttonFavorite -> {
                        replaceFragment(FavoriteFragment())
                    }
                }
                return@setOnItemSelectedListener true
            }
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment).commit()
    }





}