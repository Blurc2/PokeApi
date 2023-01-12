package com.gshp.desktop.framework.room.user

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @ColumnInfo(index = true)
    var id: Int = 0,
    var name: String = "",
    var username: String = "",
    var password: String = "",
    var accessToken: String = "",
    var refreshToken: String = "",
    var job: String = "",
    var idType: Int = 0,
    var idRole: Int = -1,
    var role: String = "",
    var lastSession: Long = -1,
    var termsAccepted: Boolean = false,
    var estatus: Int = 17, // activo o inactivo
    var idSup: Int = -1,
    var idProject: Int = 0,
    var fbToken: String? = ""
)