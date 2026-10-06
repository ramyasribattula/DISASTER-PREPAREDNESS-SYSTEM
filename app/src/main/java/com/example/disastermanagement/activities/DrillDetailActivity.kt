package com.example.disastermanagement.activities

import android.os.Bundle
import android.text.util.Linkify
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.disastermanagement.R
import com.example.disastermanagement.databinding.ActivityDrillDetailBinding
import com.example.disastermanagement.models.Drill

class DrillDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrillDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrillDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Drill Details"
        
        val drill = intent.getSerializableExtra("drill") as? Drill
        drill?.let {
            binding.textViewTitle.text = it.name
            binding.textViewDescription.text = it.description
            // Dynamically add clickable YouTube links
            val container = binding.root.findViewById<android.widget.LinearLayout>(R.id.containerLinks)
            if (it.referenceVideos.isNotEmpty()) {
                it.referenceVideos.forEach { url ->
                    val tv = TextView(this)
                    tv.text = url
                    tv.textSize = 14f
                    tv.setTextColor(getColor(R.color.primary_color))
                    tv.visibility = View.VISIBLE
                    tv.setOnClickListener { _ ->
                        val intent = android.content.Intent(this, VideoPlayerActivity::class.java)
                        intent.putExtra("video", url)
                        intent.putExtra("title", it.name)
                        startActivity(intent)
                    }
                    container.addView(tv)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
