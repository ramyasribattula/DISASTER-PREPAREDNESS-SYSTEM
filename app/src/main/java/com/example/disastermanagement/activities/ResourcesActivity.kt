package com.example.disastermanagement.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disastermanagement.R
import com.example.disastermanagement.adapters.ResourceAdapter
import com.example.disastermanagement.databinding.ActivityResourcesBinding
import com.example.disastermanagement.models.ResourceItem
import com.example.disastermanagement.models.ResourceType
import com.example.disastermanagement.utils.SharedPreferencesHelper

class ResourcesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResourcesBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var adapter: ResourceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Resources"

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupResources()
    }

    private fun setupResources() {
        val resources = getResourceItems()
        
        adapter = ResourceAdapter(resources) { resource ->
            // Handle resource download/view
            downloadResource(resource)
        }

        binding.recyclerViewResources.apply {
            layoutManager = LinearLayoutManager(this@ResourcesActivity)
            this.adapter = this@ResourcesActivity.adapter
        }
    }

    private fun getResourceItems(): List<ResourceItem> {
        return listOf(
            // Local asset PDF – place file at app/src/main/assets/Disaster_Preparedness_Guide.pdf
            ResourceItem(
                id = "disaster_preparedness_guide_pdf",
                title = "Disaster Preparedness Guide (PDF)",
                description = "Comprehensive disaster preparedness handbook (offline asset)",
                type = ResourceType.PDF,
                size = "—",
                downloadUrl = "file:///android_asset/Disaster_Preparedness_Guide.pdf"
            ),
            ResourceItem(
                id = "resource_1",
                title = "Disaster Preparedness Guide",
                description = "Comprehensive guide for disaster preparedness in educational institutions",
                type = ResourceType.PDF,
                size = "2.5 MB",
                downloadUrl = "https://example.com/preparedness-guide.pdf"
            ),
            // Earthquake module docs
            ResourceItem(
                id = "eq_checklist",
                title = "Earthquake Safety Checklist",
                description = "Printable checklist for BEFORE/DURING/AFTER earthquake actions",
                type = ResourceType.PDF,
                size = "350 KB",
                downloadUrl = "https://example.com/earthquake-checklist.pdf"
            ),
            // Fire module docs
            ResourceItem(
                id = "fire_evac_map_template",
                title = "Fire Evacuation Map Template",
                description = "Template to create building-specific evacuation maps",
                type = ResourceType.DOCUMENT,
                size = "120 KB",
                downloadUrl = "https://example.com/fire-evac-map-template.docx"
            ),
            // Flood module docs
            ResourceItem(
                id = "flood_ready_pack",
                title = "Flood Ready Pack",
                description = "Pack list and instructions for flood preparedness",
                type = ResourceType.PDF,
                size = "600 KB",
                downloadUrl = "https://example.com/flood-ready-pack.pdf"
            ),
            // Cyclone module docs
            ResourceItem(
                id = "cyclone_school_plan",
                title = "Cyclone School Response Plan",
                description = "Template plan for schools in coastal regions",
                type = ResourceType.DOCUMENT,
                size = "200 KB",
                downloadUrl = "https://example.com/cyclone-school-plan.docx"
            ),
            // Landslide module docs
            ResourceItem(
                id = "landslide_hazard_map_guide",
                title = "Landslide Hazard Mapping Guide",
                description = "How to identify and mark landslide-prone zones around campus",
                type = ResourceType.PDF,
                size = "1.1 MB",
                downloadUrl = "https://example.com/landslide-hazard-mapping.pdf"
            ),
            ResourceItem(
                id = "landslide_do_donts",
                title = "Landslide Do’s and Don’ts",
                description = "Quick reference for students and staff in hilly regions",
                type = ResourceType.PDF,
                size = "240 KB",
                downloadUrl = "https://example.com/landslide-dos-donts.pdf"
            ),
            // Drought module docs
            ResourceItem(
                id = "drought_water_conservation",
                title = "Drought: Water Conservation Handbook",
                description = "Simple measures schools can adopt to conserve water",
                type = ResourceType.PDF,
                size = "780 KB",
                downloadUrl = "https://example.com/drought-water-conservation.pdf"
            ),
            ResourceItem(
                id = "drought_school_continuity",
                title = "School Continuity Plan (Drought)",
                description = "Operational continuity planning template for drought conditions",
                type = ResourceType.DOCUMENT,
                size = "110 KB",
                downloadUrl = "https://example.com/drought-continuity-plan.docx"
            ),
            // Tsunami module docs
            ResourceItem(
                id = "tsunami_evac_routes",
                title = "Tsunami Evacuation Routes Template",
                description = "Create and mark high-ground evacuation routes",
                type = ResourceType.DOCUMENT,
                size = "95 KB",
                downloadUrl = "https://example.com/tsunami-evac-routes.docx"
            ),
            ResourceItem(
                id = "tsunami_awareness_poster",
                title = "Tsunami Awareness Poster Set",
                description = "Printable posters for coastal schools",
                type = ResourceType.IMAGE,
                size = "3.5 MB",
                downloadUrl = "https://example.com/tsunami-awareness-posters.zip"
            ),
            ResourceItem(
                id = "resource_2",
                title = "Emergency Contact Template",
                description = "Template for creating emergency contact lists",
                type = ResourceType.DOCUMENT,
                size = "150 KB",
                downloadUrl = "https://example.com/contact-template.docx"
            ),
            ResourceItem(
                id = "resource_3",
                title = "Evacuation Route Maps",
                description = "Sample evacuation route maps for different building types",
                type = ResourceType.IMAGE,
                size = "1.2 MB",
                downloadUrl = "https://example.com/evacuation-maps.zip"
            ),
            ResourceItem(
                id = "resource_4",
                title = "Disaster Response Checklist",
                description = "Step-by-step checklist for disaster response procedures",
                type = ResourceType.PDF,
                size = "800 KB",
                downloadUrl = "https://example.com/response-checklist.pdf"
            )
        )
    }

    private fun downloadResource(resource: ResourceItem) {
        // Implementation for downloading resources
        // This would typically use DownloadManager or similar
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_resources, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_download_all -> {
                // Download all resources
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

