package com.example.disastermanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disastermanagement.R
import com.example.disastermanagement.models.EmergencyContact
import com.example.disastermanagement.models.ContactCategory
import com.example.disastermanagement.utils.ColorPalette

class EmergencyContactAdapter(
    private val contacts: List<EmergencyContact>,
    private val onItemClick: (EmergencyContact) -> Unit
) : RecyclerView.Adapter<EmergencyContactAdapter.EmergencyContactViewHolder>() {

    class EmergencyContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)
        val organizationTextView: TextView = itemView.findViewById(R.id.organizationTextView)
        val roleTextView: TextView = itemView.findViewById(R.id.roleTextView)
        val categoryIconImageView: ImageView = itemView.findViewById(R.id.categoryIconImageView)
        val primaryIndicator: View = itemView.findViewById(R.id.primaryIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_emergency_contact, parent, false)
        return EmergencyContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmergencyContactViewHolder, position: Int) {
        val contact = contacts[position]
        
        holder.nameTextView.text = contact.name
        holder.phoneTextView.text = contact.phoneNumber
        holder.organizationTextView.text = contact.organization
        holder.roleTextView.text = contact.role
        
        // Set category icon
        val categoryIcon = when (contact.category) {
            ContactCategory.POLICE -> R.drawable.ic_police
            ContactCategory.FIRE_DEPARTMENT -> R.drawable.ic_fire
            ContactCategory.MEDICAL -> R.drawable.ic_medical
            ContactCategory.DISASTER_MANAGEMENT -> R.drawable.ic_disaster
            ContactCategory.SCHOOL_ADMIN -> R.drawable.ic_school
            ContactCategory.PARENT -> R.drawable.ic_parent
            ContactCategory.OTHER -> R.drawable.ic_other
        }
        holder.categoryIconImageView.setImageResource(categoryIcon)
        
        // Show/hide primary indicator
        holder.primaryIndicator.visibility = if (contact.isPrimary) View.VISIBLE else View.GONE
        
        // Palette: consistent color per category
        val key = contact.category.ordinal
        val bgColor = ColorPalette.getColorForKey(holder.itemView.context, key)
        (holder.itemView as? com.google.android.material.card.MaterialCardView)?.setCardBackgroundColor(bgColor)

        holder.itemView.setOnClickListener {
            onItemClick(contact)
        }
    }

    override fun getItemCount(): Int = contacts.size
}

