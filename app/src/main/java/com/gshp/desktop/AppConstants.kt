package com.gshp.desktop

import com.cgsa.artifacts.dtos.PreferencePair
import com.cgsa.artifacts.enums.PreferenceType

object AppConstants {

    object Preferences {
        val WORKDAY_STATUS = PreferencePair("WORKDAY_STATUS", PreferenceType.BOOLEAN)
    }
}