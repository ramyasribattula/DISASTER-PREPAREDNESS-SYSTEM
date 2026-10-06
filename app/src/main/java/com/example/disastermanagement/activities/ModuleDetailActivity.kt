package com.example.disastermanagement.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.disastermanagement.R
import com.example.disastermanagement.databinding.ActivityModuleDetailBinding
import com.example.disastermanagement.models.EducationModule

class ModuleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModuleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModuleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Module Details"
        
        val module = intent.getSerializableExtra("module") as? EducationModule
        module?.let {
            binding.textViewTitle.text = it.title
            binding.textViewDescription.text = it.description
            binding.textViewContent.text = it.content
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
