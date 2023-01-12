package com.gshp.desktop.framework.room.section

import androidx.room.Dao
import com.gshp.desktop.framework.room.BaseDao

@Dao
abstract class SectionDao:BaseDao<Section>("sections","id") {

}