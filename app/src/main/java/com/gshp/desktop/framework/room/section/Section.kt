package com.gshp.desktop.framework.room.section

import androidx.room.*
import com.gshp.desktop.framework.room.form.Form

@Entity(
    tableName = "sections",
    foreignKeys = [ForeignKey(
        entity = Form::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_form"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Section(

    @PrimaryKey
    @ColumnInfo(index = true)
    var id: Int = 0,

    @ColumnInfo(name = "id_form")
    var idForm: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "order_by")
    var order: Int = 0,

    @Ignore
    var inputs: List<Int> = ArrayList()


)