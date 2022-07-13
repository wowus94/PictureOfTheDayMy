package com.example.pictureofthedaymy.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.R
import com.example.pictureofthedaymy.databinding.ActivityBottomBarBinding
import com.example.pictureofthedaymy.view.picture.PictureOfTheDayBaseFragment
import com.example.pictureofthedaymy.view.settings.SettingsFragment

const val ThemeBlue = 1
const val ThemeGreen = 2
const val ThemeRed = 3

class BottomBarActivity : AppCompatActivity() {

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    private lateinit var binding: ActivityBottomBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTheme(getRealStyle(getCurrentTheme()))

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

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            ThemeBlue -> R.style.ThemeBlue
            ThemeGreen -> R.style.ThemeGreen
            ThemeRed -> R.style.ThemeRed
            else -> 0
        }
    }

    fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
