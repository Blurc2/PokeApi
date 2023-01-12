package com.gshp.desktop.framework.room.option

import androidx.room.Dao
import com.gshp.desktop.framework.room.BaseDao

@Dao
abstract class OptionDao:BaseDao<Option>("option","id") {
}