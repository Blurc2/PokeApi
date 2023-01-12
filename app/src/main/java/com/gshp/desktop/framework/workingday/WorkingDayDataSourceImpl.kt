package com.gshp.desktop.framework.workingday

import com.cgsa.artifacts.domain.repository.PreferenceRepository
import com.gshp.desktop.AppConstants
import com.gshp.desktop.data.workingday.WorkingDayDataSource

class WorkingDayDataSourceImpl(private val preferenceRepository: PreferenceRepository): WorkingDayDataSource {

    override fun getWorkingDayStatus() = preferenceRepository.getPreference<Boolean>(AppConstants.Preferences.WORKDAY_STATUS, false)

    override fun toggleWorkingDayStatus() {
        preferenceRepository.setOrDeletePreference(AppConstants.Preferences.WORKDAY_STATUS.key, getWorkingDayStatus().not())
    }
}