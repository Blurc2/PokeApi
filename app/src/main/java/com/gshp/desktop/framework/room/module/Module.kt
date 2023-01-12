package com.gshp.desktop.framework.room.module

import androidx.annotation.IdRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "module")
data class Module(
    @PrimaryKey
    @ColumnInfo(index = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "order")
    var order: Int = 0,
    @ColumnInfo(name = "required")
    var required: Int = 0,
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "icon")
    var icon: String = "",
    var state: Int = 0,
    @Ignore
    var forms: List<Int> = emptyList()


) {}