package com.example.finaltask

import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.finaltask.util.Constants.KEY_THEME
import com.example.finaltask.util.Constants.PREFS_NAME
import com.example.finaltask.util.Constants.THEME_DARK
import com.example.finaltask.util.Constants.THEME_LIGHT
import com.example.finaltask.util.Constants.THEME_UNDEFINED

class MainActivity : AppCompatActivity() {

    private val sharedPrefs by lazy { getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTheme()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme_light -> {
                setTheme(MODE_NIGHT_NO, THEME_LIGHT)
                return true
            }
            R.id.theme_dark -> {
                setTheme(MODE_NIGHT_YES, THEME_DARK)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTheme(themeMode: Int, prefsMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    private fun initTheme() {
        when (getSavedTheme()) {
            THEME_LIGHT -> setTheme(MODE_NIGHT_NO, THEME_LIGHT)
            THEME_DARK -> setTheme(MODE_NIGHT_YES, THEME_DARK)
            THEME_UNDEFINED -> setTheme(MODE_NIGHT_NO, THEME_LIGHT)
        }
    }

    private fun saveTheme(theme: Int) = sharedPrefs.edit().putInt(KEY_THEME, theme).apply()

    private fun getSavedTheme() = sharedPrefs.getInt(KEY_THEME, THEME_UNDEFINED)
}
