package com.example.disastermanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.disastermanagement.R
import com.example.disastermanagement.models.UserCategory

class UserCategoryAdapter(
    private val categories: List<UserCategory>,
    private val onItemClick: (UserCategory) -> Unit
) : RecyclerView.Adapter<UserCategoryAdapter.UserCategoryViewHolder>() {

    class UserCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.iconImageView)
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_category, parent, false)
        return UserCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserCategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.icon.setImageResource(item.iconRes)
        holder.title.text = item.title
        holder.description.text = item.description
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = categories.size
}


