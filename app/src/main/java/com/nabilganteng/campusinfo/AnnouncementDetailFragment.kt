package com.nabilganteng.campusinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nabilganteng.campusinfo.databinding.FragmentAnnouncementDetailBinding

class AnnouncementDetailFragment : Fragment() {
    private var _binding: FragmentAnnouncementDetailBinding? = null
    private val binding get() = _binding!!
    private val args: AnnouncementDetailFragmentArgs by navArgs()
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAnnouncementDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val announcementId = args.announcementId
        val announcement = viewModel.announcements.value?.find { it.id == announcementId }

        announcement?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvDetailContent.text = it.content
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
