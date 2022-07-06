package com.example.pictureofthedaymy.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.R
import com.example.pictureofthedaymy.databinding.ActivityBottomBarBinding

class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_view_earth -> {
                    navigateTo(EarthFragment());true
                }
                R.id.action_view_mars -> {
                    navigateTo(MarsFragment());true
                }
                R.id.action_view_system -> {
                    navigateTo(SystemFragment());true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.action_view_earth

    }

    fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
