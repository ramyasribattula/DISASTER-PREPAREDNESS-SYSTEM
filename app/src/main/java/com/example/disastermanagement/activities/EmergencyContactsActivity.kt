package com.example.disastermanagement.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disastermanagement.R
import com.example.disastermanagement.adapters.EmergencyContactAdapter
import com.example.disastermanagement.databinding.ActivityEmergencyContactsBinding
import com.example.disastermanagement.models.*
import com.example.disastermanagement.utils.SharedPreferencesHelper

class EmergencyContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmergencyContactsBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var adapter: EmergencyContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmergencyContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Emergency Contacts"

        sharedPreferencesHelper = SharedPreferencesHelper(this)

        setupEmergencyContacts()
    }

    private fun setupEmergencyContacts() {
        val contacts = getEmergencyContacts()
        
        adapter = EmergencyContactAdapter(contacts) { contact ->
            makeEmergencyCall(contact.phoneNumber)
        }

        binding.recyclerViewContacts.apply {
            layoutManager = LinearLayoutManager(this@EmergencyContactsActivity)
            this.adapter = this@EmergencyContactsActivity.adapter
        }
    }

    private fun getEmergencyContacts(): List<EmergencyContact> {
        return listOf(
            EmergencyContact(
                id = "police_100",
                name = "Police",
                phoneNumber = "100",
                email = null,
                organization = "Police Department",
                role = "Emergency Response",
                isPrimary = true,
                category = ContactCategory.POLICE
            ),
            EmergencyContact(
                id = "fire_101",
                name = "Fire Department",
                phoneNumber = "101",
                email = null,
                organization = "Fire Department",
                role = "Fire Emergency",
                isPrimary = true,
                category = ContactCategory.FIRE_DEPARTMENT
            ),
            EmergencyContact(
                id = "medical_108",
                name = "Medical Emergency",
                phoneNumber = "108",
                email = null,
                organization = "Medical Services",
                role = "Medical Emergency",
                isPrimary = true,
                category = ContactCategory.MEDICAL
            ),
            EmergencyContact(
                id = "ndma_1078",
                name = "NDMA Helpline",
                phoneNumber = "1078",
                email = "ndma@nic.in",
                organization = "National Disaster Management Authority",
                role = "Disaster Management",
                isPrimary = true,
                category = ContactCategory.DISASTER_MANAGEMENT
            ),
            EmergencyContact(
                id = "school_admin",
                name = "School Administrator",
                phoneNumber = "+91-9876543210",
                email = "admin@school.edu",
                organization = "School Administration",
                role = "School Emergency Contact",
                isPrimary = false,
                category = ContactCategory.SCHOOL_ADMIN
            ),
            EmergencyContact(
                id = "security_guard",
                name = "Security Guard",
                phoneNumber = "+91-9876543211",
                email = null,
                organization = "School Security",
                role = "Campus Security",
                isPrimary = false,
                category = ContactCategory.OTHER
            )
        )
    }

    private fun makeEmergencyCall(phoneNumber: String) {
        // Open the dialer with the number prefilled (no CALL_PHONE permission required)
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_emergency_contacts, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_contact -> {
                startActivity(Intent(this, AddContactActivity::class.java))
                true
            }
            R.id.action_edit_contacts -> {
                startActivity(Intent(this, EditContactsActivity::class.java))
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

