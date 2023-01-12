package com.gshp.desktop.framework.room.form

import androidx.room.Dao
import com.gshp.desktop.framework.room.BaseDao

@Dao
abstract class FormDao : BaseDao<Form>("form", "id") {
}