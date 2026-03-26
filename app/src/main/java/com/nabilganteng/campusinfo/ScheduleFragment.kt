package com.nabilganteng.campusinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabilganteng.campusinfo.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ScheduleAdapter()
        binding.rvSchedule.adapter = adapter
        binding.rvSchedule.layoutManager = LinearLayoutManager(requireContext())

        val scheduleData = listOf(
            ScheduleItem.Header("Senin"),
            ScheduleItem.Course("Algoritma & Struktur Data", "08:00 - 10:30", "Lab Komputer 1"),
            ScheduleItem.Course("Pemrograman Web", "13:00 - 15:30", "Ruang 204"),
            ScheduleItem.Header("Selasa"),
            ScheduleItem.Course("Kecerdasan Buatan", "09:00 - 11:30", "Ruang 301")
        )

        adapter.submitList(scheduleData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
