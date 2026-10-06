package com.example.disastermanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disastermanagement.R
import com.example.disastermanagement.models.EducationModule
import com.example.disastermanagement.models.DifficultyLevel
import com.example.disastermanagement.utils.ColorPalette

class EducationModuleAdapter(
    private val modules: List<EducationModule>,
    private val onItemClick: (EducationModule) -> Unit
) : RecyclerView.Adapter<EducationModuleAdapter.EducationModuleViewHolder>() {

    class EducationModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val disasterTypeTextView: TextView = itemView.findViewById(R.id.disasterTypeTextView)
        val difficultyTextView: TextView = itemView.findViewById(R.id.difficultyTextView)
        val durationTextView: TextView = itemView.findViewById(R.id.durationTextView)
        val regionTextView: TextView = itemView.findViewById(R.id.regionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationModuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_education_module, parent, false)
        return EducationModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: EducationModuleViewHolder, position: Int) {
        val module = modules[position]
        
        holder.titleTextView.text = module.title
        holder.descriptionTextView.text = module.description
        holder.disasterTypeTextView.text = module.disasterType.displayName
        holder.difficultyTextView.text = module.difficulty.name
        holder.durationTextView.text = "${module.duration} min"
        
        if (module.regionSpecific && module.region != null) {
            holder.regionTextView.text = "Region: ${module.region}"
            holder.regionTextView.visibility = View.VISIBLE
        } else {
            holder.regionTextView.visibility = View.GONE
        }
        
        // Set difficulty color
        val difficultyColor = when (module.difficulty) {
            DifficultyLevel.BEGINNER -> R.color.difficulty_beginner
            DifficultyLevel.INTERMEDIATE -> R.color.difficulty_intermediate
            DifficultyLevel.ADVANCED -> R.color.difficulty_advanced
        }
        holder.difficultyTextView.setTextColor(holder.itemView.context.getColor(difficultyColor))
        
        // Palette color by stable key (module id hash)
        val bgColor = ColorPalette.getColorForKey(holder.itemView.context, module.id.hashCode())
        (holder.itemView as? com.google.android.material.card.MaterialCardView)?.setCardBackgroundColor(bgColor)

        holder.itemView.setOnClickListener {
            onItemClick(module)
        }
    }

    override fun getItemCount(): Int = modules.size
}

