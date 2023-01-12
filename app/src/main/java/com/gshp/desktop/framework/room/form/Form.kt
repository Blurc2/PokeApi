package com.gshp.desktop.framework.room.form

import androidx.room.*
import com.gshp.desktop.framework.room.module.Module

@Entity(
    tableName = "form",
    foreignKeys = [ForeignKey(
        entity = Module::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("module_id"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Form(

    @PrimaryKey
    @ColumnInfo(index = true)
    var id: Int = 0,

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "module_id")
    var idModule: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "order")
    var order: Int = 0,

    @ColumnInfo(name = "sections")
    var sections: MutableList<String> = ArrayList()
)