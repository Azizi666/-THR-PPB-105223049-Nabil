package com.nabilganteng.campusinfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nabilganteng.campusinfo.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("ProfilePref", Context.MODE_PRIVATE)
        
        // Ambil data awal dari SharedPreferences jika ada
        val savedName = sharedPref.getString("USER_NAME", "Nabil Hafizuddin A.")
        viewModel.updateUserName(savedName ?: "Nabil Hafizuddin A.")

        viewModel.userName.observe(viewLifecycleOwner) { name ->
            binding.etName.setText(name)
        }
        
        binding.tvNim.text = "105223049"

        binding.btnSave.setOnClickListener {
            val newName = binding.etName.text.toString()
            if (newName.isNotBlank()) {
                // Simpan ke ViewModel (untuk update UI fragment lain segera)
                viewModel.updateUserName(newName)
                
                // Simpan ke SharedPreferences (untuk persistensi saat app di-restart)
                sharedPref.edit().putString("USER_NAME", newName).apply()
                
                Toast.makeText(context, "Profil diperbarui!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
