package com.example.disastermanagement.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disastermanagement.R
import com.example.disastermanagement.adapters.EducationModuleAdapter
import com.example.disastermanagement.databinding.ActivityEducationBinding
import com.example.disastermanagement.models.*
import com.example.disastermanagement.utils.SharedPreferencesHelper

class EducationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEducationBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var adapter: EducationModuleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Disaster Education"

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupEducationModules()
    }

    private fun setupEducationModules() {
        val modules = getEducationModules()
        
        adapter = EducationModuleAdapter(modules) { module ->
            val intent = Intent(this, ModuleDetailActivity::class.java)
            intent.putExtra("module", module)
            startActivity(intent)
        }

        binding.recyclerViewModules.apply {
            layoutManager = LinearLayoutManager(this@EducationActivity)
            this.adapter = this@EducationActivity.adapter
        }
    }

    private fun getEducationModules(): List<EducationModule> {
        val userRegion = sharedPreferencesHelper.getRegion() ?: "General"
        
        return listOf(
            EducationModule(
                id = "earthquake_basic",
                title = "Earthquake Safety Basics",
                description = "Learn essential earthquake safety procedures and what to do during and after an earthquake.",
                disasterType = DisasterType.EARTHQUAKE,
                content = "Earthquakes are sudden, rapid shaking of the ground caused by the breaking and shifting of rock beneath the Earth's surface...",
                quiz = listOf(
                    QuizQuestion(
                        question = "What should you do during an earthquake?",
                        options = listOf("Run outside", "Drop, Cover, and Hold On", "Stand in a doorway", "Go to the basement"),
                        correctAnswer = 1,
                        explanation = "The safest action during an earthquake is to Drop, Cover, and Hold On under a sturdy table or desk."
                    )
                ),
                duration = 15,
                difficulty = DifficultyLevel.BEGINNER,
                regionSpecific = true,
                region = userRegion
            ),
            EducationModule(
                id = "flood_safety",
                title = "Flood Preparedness",
                description = "Understand flood risks and learn how to prepare for and respond to flooding situations.",
                disasterType = DisasterType.FLOOD,
                content = "Floods are among the most frequent and costly natural disasters. They can occur with little or no warning...",
                quiz = listOf(
                    QuizQuestion(
                        question = "What should you do if you see floodwaters rising?",
                        options = listOf("Wait and see", "Move to higher ground immediately", "Stay in your car", "Try to drive through"),
                        correctAnswer = 1,
                        explanation = "Always move to higher ground immediately when you see floodwaters rising. Never try to drive through flooded areas."
                    )
                ),
                duration = 20,
                difficulty = DifficultyLevel.BEGINNER,
                regionSpecific = true,
                region = userRegion
            ),
            EducationModule(
                id = "fire_evacuation",
                title = "Fire Evacuation Procedures",
                description = "Learn proper fire evacuation techniques and fire safety measures for educational institutions.",
                disasterType = DisasterType.FIRE,
                content = "Fire safety in schools and colleges is crucial. Knowing the proper evacuation procedures can save lives...",
                quiz = listOf(
                    QuizQuestion(
                        question = "What is the first thing you should do when you hear a fire alarm?",
                        options = listOf("Continue working", "Evacuate immediately", "Check if it's real", "Call the fire department"),
                        correctAnswer = 1,
                        explanation = "Always evacuate immediately when you hear a fire alarm. Never assume it's a false alarm."
                    )
                ),
                duration = 12,
                difficulty = DifficultyLevel.BEGINNER,
                regionSpecific = false
            ),
            EducationModule(
                id = "cyclone_preparedness",
                title = "Cyclone Preparedness",
                description = "Learn about cyclone formation, warning systems, and safety measures for coastal regions.",
                disasterType = DisasterType.CYCLONE,
                content = "Cyclones are powerful rotating storms that can cause devastating damage. Understanding warning systems is crucial...",
                quiz = listOf(
                    QuizQuestion(
                        question = "What should you do when a cyclone warning is issued?",
                        options = listOf("Go to the beach", "Stay indoors and away from windows", "Continue outdoor activities", "Drive to another city"),
                        correctAnswer = 1,
                        explanation = "When a cyclone warning is issued, stay indoors, away from windows, and follow official instructions."
                    )
                ),
                duration = 25,
                difficulty = DifficultyLevel.INTERMEDIATE,
                regionSpecific = true,
                region = "Coastal Areas"
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_education, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_quiz -> {
                startActivity(Intent(this, QuizActivity::class.java))
                true
            }
            R.id.action_progress -> {
                startActivity(Intent(this, ProgressActivity::class.java))
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

