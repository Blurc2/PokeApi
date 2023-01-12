package com.gshp.desktop.framework.room.report

import androidx.room.Dao
import com.gshp.desktop.framework.room.BaseDao

@Dao
abstract class ReportDao: BaseDao<Report>("report","id") {

}