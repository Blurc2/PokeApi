package com.gshp.desktop.data.workingday

interface WorkingDayDataSource {
    fun toggleWorkingDayStatus()
    fun getWorkingDayStatus(): Boolean
}