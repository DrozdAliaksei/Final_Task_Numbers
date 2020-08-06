package com.example.stage2task6.mvp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.example.stage2task6.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.light_theme_settings -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                return true
            }
            R.id.dark_theme_settings -> {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
