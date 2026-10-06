package com.example.disastermanagement.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disastermanagement.R
import com.example.disastermanagement.adapters.DrillAdapter
import com.example.disastermanagement.databinding.ActivityDrillsBinding
import com.example.disastermanagement.models.*
import com.example.disastermanagement.utils.SharedPreferencesHelper
import java.util.Date

class DrillsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrillsBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var adapter: DrillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrillsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Virtual Drills"

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupDrills()
    }

    private fun setupDrills() {
        val drills = getAvailableDrills()
        
        adapter = DrillAdapter(drills) { drill ->
            val intent = Intent(this, DrillDetailActivity::class.java)
            intent.putExtra("drill", drill)
            startActivity(intent)
        }

        binding.recyclerViewDrills.apply {
            layoutManager = LinearLayoutManager(this@DrillsActivity)
            this.adapter = this@DrillsActivity.adapter
        }
    }

    private fun getAvailableDrills(): List<Drill> {
        val res = resources
        val eq = res.getStringArray(R.array.earthquake_videos).toList()
        val fire = res.getStringArray(R.array.fire_videos).toList()
        val flood = res.getStringArray(R.array.flood_videos).toList()
        val cyclone = res.getStringArray(R.array.cyclone_videos).toList()

        return listOf(
            Drill(
                id = "earthquake_drill_1",
                name = "Earthquake Evacuation Drill",
                disasterType = DisasterType.EARTHQUAKE,
                description = "Practice earthquake evacuation procedures in a virtual environment",
                steps = listOf(
                    DrillStep(1, "Drop, Cover, and Hold On", "Find a sturdy table or desk and get under it", 10),
                    DrillStep(2, "Wait for shaking to stop", "Stay under cover until the shaking completely stops", 30),
                    DrillStep(3, "Check for injuries", "Assess yourself and others for injuries", 15),
                    DrillStep(4, "Evacuate safely", "Exit the building using designated evacuation routes", 60),
                    DrillStep(5, "Assemble at meeting point", "Go to the designated assembly area", 30)
                ),
                duration = 10,
                difficulty = DifficultyLevel.BEGINNER,
                isVirtual = true,
                referenceVideos = eq
            ),
            Drill(
                id = "fire_drill_1",
                name = "Fire Evacuation Drill",
                disasterType = DisasterType.FIRE,
                description = "Practice fire evacuation procedures and safety protocols",
                steps = listOf(
                    DrillStep(1, "Sound the alarm", "Activate the fire alarm system", 5),
                    DrillStep(2, "Evacuate immediately", "Leave the building using the nearest safe exit", 45),
                    DrillStep(3, "Close doors behind you", "Close all doors as you exit to slow fire spread", 10),
                    DrillStep(4, "Use stairs, not elevators", "Always use stairs during fire evacuation", 20),
                    DrillStep(5, "Assemble at meeting point", "Gather at the designated assembly area", 30),
                    DrillStep(6, "Account for everyone", "Take attendance and report missing persons", 15)
                ),
                duration = 8,
                difficulty = DifficultyLevel.BEGINNER,
                isVirtual = true,
                referenceVideos = fire
            ),
            Drill(
                id = "flood_drill_1",
                name = "Flood Preparedness Drill",
                disasterType = DisasterType.FLOOD,
                description = "Practice flood preparedness and evacuation procedures",
                steps = listOf(
                    DrillStep(1, "Monitor weather alerts", "Check for flood warnings and updates", 10),
                    DrillStep(2, "Secure important documents", "Gather essential documents and valuables", 20),
                    DrillStep(3, "Move to higher ground", "Evacuate to higher floors or elevated areas", 30),
                    DrillStep(4, "Avoid floodwaters", "Never walk or drive through floodwaters", 15),
                    DrillStep(5, "Stay informed", "Continue monitoring weather updates", 10)
                ),
                duration = 12,
                difficulty = DifficultyLevel.INTERMEDIATE,
                isVirtual = true,
                referenceVideos = flood
            ),
            Drill(
                id = "cyclone_drill_1",
                name = "Cyclone Preparedness Drill",
                disasterType = DisasterType.CYCLONE,
                description = "Practice cyclone preparedness and shelter procedures",
                steps = listOf(
                    DrillStep(1, "Secure outdoor items", "Bring in or secure outdoor furniture and objects", 15),
                    DrillStep(2, "Close all windows and doors", "Seal all openings to prevent wind damage", 10),
                    DrillStep(3, "Move to safe room", "Go to an interior room without windows", 5),
                    DrillStep(4, "Stay low and cover", "Get low to the ground and cover your head", 20),
                    DrillStep(5, "Wait for all-clear", "Stay in shelter until authorities give all-clear", 30)
                ),
                duration = 15,
                difficulty = DifficultyLevel.INTERMEDIATE,
                isVirtual = true,
                referenceVideos = cyclone
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_drills, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_schedule_drill -> {
                startActivity(Intent(this, ScheduleDrillActivity::class.java))
                true
            }
            R.id.action_drill_history -> {
                startActivity(Intent(this, DrillHistoryActivity::class.java))
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

