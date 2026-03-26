package com.nabilganteng.campusinfo

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nabilganteng.campusinfo.databinding.ItemAnnouncementBinding

class AnnouncementAdapter(private val onItemClick: (Announcement) -> Unit) :
    ListAdapter<Announcement, AnnouncementAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: ItemAnnouncementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnnouncementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            tvTitle.text = item.title
            tvDate.text = item.date
            tvCategory.text = item.category
            
            val (bgColor, textColor) = when (item.category.lowercase()) {
                "akademik" -> Pair(R.color.category_akademik, R.color.category_akademik_text)
                "event" -> Pair(R.color.category_event, R.color.category_event_text)
                "it" -> Pair(R.color.category_it, R.color.category_it_text)
                "organisasi" -> Pair(R.color.category_organisasi, R.color.category_organisasi_text)
                else -> Pair(android.R.color.darker_gray, android.R.color.white)
            }

            val shape = GradientDrawable().apply {
                cornerRadius = 100f
                setColor(ContextCompat.getColor(root.context, bgColor))
            }
            tvCategory.background = shape
            tvCategory.setTextColor(ContextCompat.getColor(root.context, textColor))

            root.setOnClickListener { onItemClick(item) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Announcement>() {
        override fun areItemsTheSame(oldItem: Announcement, newItem: Announcement) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Announcement, newItem: Announcement) = oldItem == newItem
    }
}
