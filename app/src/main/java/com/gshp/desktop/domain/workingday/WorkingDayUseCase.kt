package com.gshp.desktop.domain.workingday

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WorkingDayUseCase(private val workingDayRepository: WorkingDayRepository) {

    fun getWorkingDayStatus() = workingDayRepository.getWorkingDayStatus()

    fun toggleWorkingDayStatus() {
        workingDayRepository.toggleWorkingDayStatus()
    }
}