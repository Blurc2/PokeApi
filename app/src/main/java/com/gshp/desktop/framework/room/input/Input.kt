package com.gshp.desktop.framework.room.input

import androidx.room.*
import com.gshp.desktop.framework.room.section.Section

@Entity(
    tableName = "input",
    foreignKeys = [ForeignKey(
        entity = Section::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_section"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Input(

    @PrimaryKey
    @ColumnInfo(index = true)
    var id: Int = 0,

    @ColumnInfo(name = "children")
    var children: List<Int> = ArrayList(),

    @ColumnInfo(name = "id_section")
    var idSection: Int = 0,

    @ColumnInfo(name = "id_input_type")
    var idInputType: Int = 0,

    @ColumnInfo(name = "answer_default")
    var answerDefault: String = "",

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "required")
    var required: Boolean = false,

    @ColumnInfo(name = "read_only")
    var readOnly: Boolean = false,

    @ColumnInfo(name = "order_by")
    var order: Int = 0,
    @ColumnInfo(name = "min")
    var min: Double = 0.0,
    @ColumnInfo(name = "max")
    var max: Double = 0.0,
    @ColumnInfo(name = "file")
    var filePhoto: String? = "",
    @ColumnInfo(name = "visible")
    var visible: Boolean = true,

    @Ignore
    var option: List<Int> = ArrayList(),
    @ColumnInfo(name = "regex")
    var regex: String = "",
    @ColumnInfo(name = "parents")
    var parents: List<Int> = ArrayList(),
    @ColumnInfo(name = "parentOption")
    var parentOption: List<Int> = ArrayList(),
    var status: Int = 0


)