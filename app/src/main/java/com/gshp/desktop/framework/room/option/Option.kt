package com.gshp.desktop.framework.room.option

import androidx.room.*
import com.gshp.desktop.framework.room.input.Input

@Entity(
    tableName = "option",
    foreignKeys = [ForeignKey(
        entity = Input::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id_input"),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class Option(

    @PrimaryKey
    @ColumnInfo(index = true)
    var id: Int = 0,

    @ColumnInfo(name = "id_input")
    var idInput: Int = 0,

    @ColumnInfo(name = "value")
    var value: String = "",

    @ColumnInfo(name = "order_by")
    var order: Int = 0,

//    @ColumnInfo(name = "inputs_event")
    @ColumnInfo(name = "input_events")
    var inputsEvent: HashMap<Int, ArrayList<Int>?> = HashMap(),

    @ColumnInfo(name = "Icon")
    var icon: String = "",
    @ColumnInfo(name = "file")
    var filePhoto: String? = "",
    var status: Int = 0,
    @Ignore
    var visible: Boolean = true


)