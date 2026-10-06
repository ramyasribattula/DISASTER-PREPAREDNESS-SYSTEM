package com.example.disastermanagement

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.disastermanagement.databinding.ActivityMainBinding
import com.example.disastermanagement.adapters.MainMenuAdapter
import com.example.disastermanagement.models.MainMenuItem
import com.example.disastermanagement.activities.*
import com.example.disastermanagement.utils.SharedPreferencesHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Disaster Management"

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupMainMenu()
    }

    private fun setupMainMenu() {
        val menuItems = listOf(
            MainMenuItem("Education", "Learn about disasters", R.drawable.ic_education, "education"),
            MainMenuItem("Virtual Drills", "Practice emergency procedures", R.drawable.ic_drill, "drills"),
            MainMenuItem("Emergency Contacts", "Quick access to help", R.drawable.ic_contacts, "contacts"),
            MainMenuItem("Alerts", "Real-time notifications", R.drawable.ic_alerts, "alerts"),
            MainMenuItem("Admin Dashboard", "Manage institution", R.drawable.ic_admin, "admin"),
            MainMenuItem("Resources", "Download materials", R.drawable.ic_resources, "resources")
        )

        val adapter = MainMenuAdapter(menuItems) { menuItem ->
            when (menuItem.id) {
                "education" -> startActivity(Intent(this, EducationActivity::class.java))
                "drills" -> startActivity(Intent(this, DrillsActivity::class.java))
                "contacts" -> startActivity(Intent(this, EmergencyContactsActivity::class.java))
                "alerts" -> startActivity(Intent(this, AlertsActivity::class.java))
                "admin" -> startActivity(Intent(this, AdminDashboardActivity::class.java))
                "resources" -> startActivity(Intent(this, ResourcesActivity::class.java))
            }
        }

        binding.recyclerViewMainMenu.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            this.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}