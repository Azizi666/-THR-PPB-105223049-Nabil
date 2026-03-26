package com.nabilganteng.campusinfo

sealed class ScheduleItem {
    data class Header(val day: String) : ScheduleItem()
    data class Course(val name: String, val time: String, val room: String) : ScheduleItem()
}