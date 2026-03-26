package com.nabilganteng.campusinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Announcement(val id: Int, val title: String, val date: String, val category: String, val content: String)

class HomeViewModel : ViewModel() {
    private val _userName = MutableLiveData<String>("Nabil Hafizuddin A.")
    val userName: LiveData<String> = _userName

    fun updateUserName(newName: String) {
        _userName.value = newName
    }

    private val allAnnouncements = listOf(
        Announcement(1, "Registrasi Semester Genap", "10 Jan 2026", "Akademik", "Registrasi ulang mahasiswa Universitas Pertamina dibuka..."),
        Announcement(2, "Lomba CITE UP 2026", "12 Jan 2026", "Event", "Pendaftaran divisi acara telah dibuka..."),
        Announcement(3, "Maintenance Server E-Learning", "15 Jan 2026", "IT", "Server akan down selama 2 jam..."),
        Announcement(4, "Jadwal Asisten Praktikum ASD", "18 Jan 2026", "Akademik", "Jadwal asisten dosen telah dirilis..."),
        Announcement(5, "Pendaftaran HMIK FEST", "20 Jan 2026", "Organisasi", "Jangan lewatkan festival terbesar tahun ini!")
    )

    private val _announcements = MutableLiveData<List<Announcement>>(allAnnouncements)
    val announcements: LiveData<List<Announcement>> = _announcements

    fun filterAnnouncements(query: String) {
        if (query.isBlank()) {
            _announcements.value = allAnnouncements
        } else {
            _announcements.value = allAnnouncements.filter {
                it.category.contains(query, ignoreCase = true) || it.title.contains(query, ignoreCase = true)
            }
        }
    }
}
