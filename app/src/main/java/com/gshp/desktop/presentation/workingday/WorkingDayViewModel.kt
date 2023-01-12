package com.gshp.desktop.presentation.workingday

import androidx.lifecycle.ViewModel
import com.gshp.desktop.domain.workingday.WorkingDayUseCase

class WorkingDayViewModel(private val workingDayUseCase: WorkingDayUseCase) : ViewModel() {

    fun getWorkingDayStatus() = workingDayUseCase.getWorkingDayStatus()

    fun toggleWorkingDayStatus() {
        workingDayUseCase.toggleWorkingDayStatus()
    }
}