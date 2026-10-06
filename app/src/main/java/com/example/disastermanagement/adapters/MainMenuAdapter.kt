package com.example.disastermanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disastermanagement.R
import com.example.disastermanagement.models.MainMenuItem
import com.example.disastermanagement.utils.ColorPalette

class MainMenuAdapter(
    private val menuItems: List<MainMenuItem>,
    private val onItemClick: (MainMenuItem) -> Unit
) : RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>() {

    class MainMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_menu, parent, false)
        return MainMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        val menuItem = menuItems[position]
        
        holder.iconImageView.setImageResource(menuItem.iconRes)
        holder.titleTextView.text = menuItem.title
        holder.descriptionTextView.text = menuItem.description
        // Apply a nice background tint using our palette (index-based)
        val bgColor = ColorPalette.getColorForIndex(holder.itemView.context, position)
        (holder.itemView as? com.google.android.material.card.MaterialCardView)?.setCardBackgroundColor(bgColor)
        
        holder.itemView.setOnClickListener {
            onItemClick(menuItem)
        }
    }

    override fun getItemCount(): Int = menuItems.size
}

