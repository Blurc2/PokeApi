package com.gshp.desktop.framework.room.input

import androidx.room.Dao
import com.gshp.desktop.framework.room.BaseDao

@Dao
abstract class InputDao : BaseDao<Input>("input", "id") {

}