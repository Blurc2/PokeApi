package com.gshp.desktop.data.workingday

import com.gshp.desktop.domain.workingday.WorkingDayRepository

class WorkingDayRepositoryImpl(private val workingDayDataSource: WorkingDayDataSource) : WorkingDayRepository {

    override fun getWorkingDayStatus() = workingDayDataSource.getWorkingDayStatus()

    override fun toggleWorkingDayStatus() {
        workingDayDataSource.toggleWorkingDayStatus()
    }
}