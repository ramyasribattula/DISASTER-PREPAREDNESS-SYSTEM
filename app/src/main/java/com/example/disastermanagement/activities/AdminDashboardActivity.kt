package com.example.disastermanagement.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import com.example.disastermanagement.R
import com.example.disastermanagement.databinding.ActivityAdminDashboardBinding
import com.example.disastermanagement.utils.SharedPreferencesHelper
import com.example.disastermanagement.adapters.UserCategoryAdapter
import com.example.disastermanagement.models.UserCategory
import com.example.disastermanagement.models.UserCategoryType

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Admin Dashboard"

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupDashboard()
    }

    private fun setupDashboard() {
        // Setup admin dashboard with statistics and management options
        binding.textViewWelcome.text = "Welcome, ${sharedPreferencesHelper.getUserName() ?: "Admin"}"
        binding.textViewInstitution.text = sharedPreferencesHelper.getInstitution() ?: "Educational Institution"
        
        // Setup click listeners for admin functions
        binding.cardViewUsers.setOnClickListener {
            // Navigate to user management
        }
        
        binding.cardViewDrills.setOnClickListener {
            // Navigate to drill management
        }
        
        binding.cardViewReports.setOnClickListener {
            // Navigate to reports
        }
        
        binding.cardViewSettings.setOnClickListener {
            // Navigate to admin settings
        }

        // User categories list
        val categories = listOf(
            UserCategory(
                type = UserCategoryType.STUDENTS,
                title = "Students",
                description = "K-12 and higher education",
                iconRes = R.drawable.ic_students
            ),
            UserCategory(
                type = UserCategoryType.TEACHERS_STAFF,
                title = "Teachers & Staff",
                description = "Teachers and administrative staff",
                iconRes = R.drawable.ic_teachers
            ),
            UserCategory(
                type = UserCategoryType.INSTITUTIONS_RESPONSE_TEAMS,
                title = "Institutions & Response Teams",
                description = "Educational institutions and local disaster response teams",
                iconRes = R.drawable.ic_institutions
            ),
            UserCategory(
                type = UserCategoryType.PARENTS_GUARDIANS,
                title = "Parents & Guardians",
                description = "Parents and guardians",
                iconRes = R.drawable.ic_parents
            ),
            UserCategory(
                type = UserCategoryType.GOVERNMENT_DEPARTMENTS,
                title = "Government Departments",
                description = "NDMA, Education Ministry",
                iconRes = R.drawable.ic_government
            )
        )

        val adapter = UserCategoryAdapter(categories) { category ->
            // Handle click per category (placeholder)
            // Future: navigate to category-specific management screens
        }
        binding.recyclerViewUserCategories.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUserCategories.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_admin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                // Handle logout
                finish()
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

