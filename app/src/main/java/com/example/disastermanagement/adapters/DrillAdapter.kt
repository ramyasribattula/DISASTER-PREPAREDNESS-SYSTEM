package com.example.disastermanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disastermanagement.R
import com.example.disastermanagement.models.Drill
import com.example.disastermanagement.models.DifficultyLevel
import com.example.disastermanagement.utils.ColorPalette

class DrillAdapter(
    private val drills: List<Drill>,
    private val onItemClick: (Drill) -> Unit
) : RecyclerView.Adapter<DrillAdapter.DrillViewHolder>() {

    class DrillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val disasterTypeTextView: TextView = itemView.findViewById(R.id.disasterTypeTextView)
        val difficultyTextView: TextView = itemView.findViewById(R.id.difficultyTextView)
        val durationTextView: TextView = itemView.findViewById(R.id.durationTextView)
        val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrillViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_drill, parent, false)
        return DrillViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrillViewHolder, position: Int) {
        val drill = drills[position]
        
        holder.titleTextView.text = drill.name
        holder.descriptionTextView.text = drill.description
        holder.disasterTypeTextView.text = drill.disasterType.displayName
        holder.difficultyTextView.text = drill.difficulty.name
        holder.durationTextView.text = "${drill.duration} min"
        
        val statusText = if (drill.isCompleted) "Completed" else "Available"
        holder.statusTextView.text = statusText
        
        // Set difficulty color
        val difficultyColor = when (drill.difficulty) {
            DifficultyLevel.BEGINNER -> R.color.difficulty_beginner
            DifficultyLevel.INTERMEDIATE -> R.color.difficulty_intermediate
            DifficultyLevel.ADVANCED -> R.color.difficulty_advanced
        }
        holder.difficultyTextView.setTextColor(holder.itemView.context.getColor(difficultyColor))
        
        // Set status color
        val statusColor = if (drill.isCompleted) R.color.difficulty_beginner else R.color.primary_color
        holder.statusTextView.setTextColor(holder.itemView.context.getColor(statusColor))
        
        // Dynamic palette background using index
        val bgColor = ColorPalette.getColorForIndex(holder.itemView.context, position)
        (holder.itemView as? com.google.android.material.card.MaterialCardView)?.setCardBackgroundColor(bgColor)

        holder.itemView.setOnClickListener {
            onItemClick(drill)
        }
    }

    override fun getItemCount(): Int = drills.size
}

