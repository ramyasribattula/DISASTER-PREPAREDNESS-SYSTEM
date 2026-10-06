package com.example.disastermanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disastermanagement.R
import com.example.disastermanagement.models.ResourceItem
import com.example.disastermanagement.models.ResourceType
import com.example.disastermanagement.utils.ColorPalette

class ResourceAdapter(
    private val resources: List<ResourceItem>,
    private val onItemClick: (ResourceItem) -> Unit
) : RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>() {

    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val sizeTextView: TextView = itemView.findViewById(R.id.sizeTextView)
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        val downloadStatusView: View = itemView.findViewById(R.id.downloadStatusView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resource, parent, false)
        return ResourceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val resource = resources[position]
        
        holder.titleTextView.text = resource.title
        holder.descriptionTextView.text = resource.description
        holder.typeTextView.text = resource.type.name
        holder.sizeTextView.text = resource.size
        
        // Set resource type icon
        val typeIcon = when (resource.type) {
            ResourceType.PDF -> R.drawable.ic_pdf
            ResourceType.DOCUMENT -> R.drawable.ic_document
            ResourceType.IMAGE -> R.drawable.ic_image
            ResourceType.VIDEO -> R.drawable.ic_video
            ResourceType.AUDIO -> R.drawable.ic_audio
            ResourceType.OTHER -> R.drawable.ic_file
        }
        holder.iconImageView.setImageResource(typeIcon)
        
        // Show/hide download status
        holder.downloadStatusView.visibility = if (resource.isDownloaded) View.VISIBLE else View.GONE
        
        // Palette color based on index for variety
        val bgColor = ColorPalette.getColorForIndex(holder.itemView.context, position)
        (holder.itemView as? com.google.android.material.card.MaterialCardView)?.setCardBackgroundColor(bgColor)

        holder.itemView.setOnClickListener {
            onItemClick(resource)
        }
    }

    override fun getItemCount(): Int = resources.size
}

