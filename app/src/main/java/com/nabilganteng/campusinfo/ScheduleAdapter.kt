package com.nabilganteng.campusinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nabilganteng.campusinfo.databinding.ItemScheduleCourseBinding

class ScheduleAdapter : ListAdapter<ScheduleItem, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_COURSE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ScheduleItem.Header -> TYPE_HEADER
            is ScheduleItem.Course -> TYPE_COURSE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_HEADER) {
            val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            HeaderViewHolder(view)
        } else {
            val binding = ItemScheduleCourseBinding.inflate(inflater, parent, false)
            CourseViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is HeaderViewHolder && item is ScheduleItem.Header) {
            val textView = holder.itemView.findViewById<TextView>(android.R.id.text1)
            textView.text = item.day
            textView.textSize = 18f
            textView.setTypeface(null, android.graphics.Typeface.BOLD)
        } else if (holder is CourseViewHolder && item is ScheduleItem.Course) {
            holder.binding.tvCourseName.text = item.name
            holder.binding.tvTime.text = item.time
            holder.binding.tvRoom.text = "Ruang: ${item.room}"
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class CourseViewHolder(val binding: ItemScheduleCourseBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<ScheduleItem>() {
        override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem) = oldItem == newItem
    }
}
