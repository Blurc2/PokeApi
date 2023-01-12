package com.gshp.desktop.framework.room.module

import androidx.room.Dao
import com.gshp.desktop.framework.room.BaseDao

@Dao
abstract class ModuleDao:BaseDao<Module>("module","id") {


}