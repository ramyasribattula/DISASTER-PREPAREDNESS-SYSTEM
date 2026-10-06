package com.example.disastermanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disastermanagement.R
import com.example.disastermanagement.models.Alert
import com.example.disastermanagement.models.AlertSeverity
import com.example.disastermanagement.utils.ColorPalette
import java.text.SimpleDateFormat
import java.util.*

class AlertAdapter(
    private val alerts: List<Alert>,
    private val onItemClick: (Alert) -> Unit
) : RecyclerView.Adapter<AlertAdapter.AlertViewHolder>() {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())

    class AlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
        val severityTextView: TextView = itemView.findViewById(R.id.severityTextView)
        val actionTextView: TextView = itemView.findViewById(R.id.actionTextView)
        val unreadIndicator: View = itemView.findViewById(R.id.unreadIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alert, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        val alert = alerts[position]
        
        holder.titleTextView.text = alert.title
        holder.messageTextView.text = alert.message
        holder.timestampTextView.text = dateFormat.format(alert.timestamp)
        
        // Set severity
        holder.severityTextView.text = alert.severity.name
        val severityColor = when (alert.severity) {
            AlertSeverity.LOW -> R.color.alert_low
            AlertSeverity.MEDIUM -> R.color.alert_medium
            AlertSeverity.HIGH -> R.color.alert_high
            AlertSeverity.CRITICAL -> R.color.alert_critical
        }
        holder.severityTextView.setTextColor(holder.itemView.context.getColor(severityColor))
        
        // Show/hide action text
        if (alert.actionRequired && alert.actionText != null) {
            holder.actionTextView.text = alert.actionText
            holder.actionTextView.visibility = View.VISIBLE
        } else {
            holder.actionTextView.visibility = View.GONE
        }
        
        // Show/hide unread indicator
        holder.unreadIndicator.visibility = if (alert.isRead) View.GONE else View.VISIBLE
        
        // Give each alert card a subtle colored background by key
        val bgColor = ColorPalette.getColorForKey(holder.itemView.context, alert.id.hashCode())
        (holder.itemView as? com.google.android.material.card.MaterialCardView)?.setCardBackgroundColor(bgColor)

        holder.itemView.setOnClickListener {
            onItemClick(alert)
        }
    }

    override fun getItemCount(): Int = alerts.size
}

