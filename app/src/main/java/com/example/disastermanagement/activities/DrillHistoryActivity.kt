package com.example.disastermanagement.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.disastermanagement.R
import com.example.disastermanagement.databinding.ActivityDrillHistoryBinding

class DrillHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrillHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrillHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Drill History"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
