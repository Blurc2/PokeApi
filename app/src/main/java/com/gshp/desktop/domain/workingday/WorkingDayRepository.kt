package com.gshp.desktop.domain.workingday

interface WorkingDayRepository {
    fun getWorkingDayStatus(): Boolean
    fun toggleWorkingDayStatus()
}