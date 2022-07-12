package com.example.pictureofthedaymy.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.R
import com.example.pictureofthedaymy.databinding.ActivityBottomBarBinding
import com.example.pictureofthedaymy.view.picture.PictureOfTheDayBaseFragment
import com.example.pictureofthedaymy.view.settings.SettingsFragment

class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_view_earth -> {
                    navigateTo(PictureOfTheDayBaseFragment.newInstance());true
                }
                R.id.action_view_mars -> {
                    navigateTo(MarsFragment());true
                }
                R.id.action_view_system -> {
                    navigateTo(SettingsFragment.newInstance());true
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
