package com.example.disastermanagement.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disastermanagement.R
import com.example.disastermanagement.adapters.AlertAdapter
import com.example.disastermanagement.databinding.ActivityAlertsBinding
import com.example.disastermanagement.models.*
import com.example.disastermanagement.utils.SharedPreferencesHelper
import java.util.Date

class AlertsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlertsBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var adapter: AlertAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlertsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Disaster Alerts"

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupAlerts()
    }

    private fun setupAlerts() {
        val alerts = getAlerts()
        
        adapter = AlertAdapter(alerts) { alert ->
            // Handle alert click - could show details or mark as read
            markAlertAsRead(alert.id)
        }

        binding.recyclerViewAlerts.apply {
            layoutManager = LinearLayoutManager(this@AlertsActivity)
            this.adapter = this@AlertsActivity.adapter
        }
    }

    private fun getAlerts(): List<Alert> {
        return listOf(
            Alert(
                id = "alert_1",
                title = "Heavy Rain Warning",
                message = "Heavy rainfall expected in your area. Avoid low-lying areas and stay indoors if possible.",
                disasterType = DisasterType.FLOOD,
                severity = AlertSeverity.MEDIUM,
                timestamp = Date(),
                isRead = false,
                actionRequired = true,
                actionText = "Check evacuation routes"
            ),
            Alert(
                id = "alert_2",
                title = "Fire Safety Reminder",
                message = "Monthly fire drill scheduled for tomorrow at 10 AM. All students and staff must participate.",
                disasterType = DisasterType.FIRE,
                severity = AlertSeverity.LOW,
                timestamp = Date(System.currentTimeMillis() - 3600000), // 1 hour ago
                isRead = true,
                actionRequired = false
            ),
            Alert(
                id = "alert_3",
                title = "Earthquake Preparedness",
                message = "Review earthquake safety procedures. Keep emergency kit ready and identify safe spots in your building.",
                disasterType = DisasterType.EARTHQUAKE,
                severity = AlertSeverity.LOW,
                timestamp = Date(System.currentTimeMillis() - 7200000), // 2 hours ago
                isRead = true,
                actionRequired = false
            )
        )
    }

    private fun markAlertAsRead(alertId: String) {
        // Implementation to mark alert as read
        // This would typically update the database or shared preferences
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_alerts, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                // Refresh alerts
                setupAlerts()
                true
            }
            R.id.action_settings -> {
                startActivity(android.content.Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

