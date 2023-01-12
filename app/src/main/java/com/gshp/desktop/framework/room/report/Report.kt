package com.gshp.desktop.framework.room.report

import androidx.room.*
import com.cgsa.artifacts.base.baseRoom.entity.Geo
import com.cgsa.artifacts.base.baseRoom.entity.Pdv
import com.cgsa.artifacts.base.baseRoom.entity.ScheduleC
import com.cgsa.artifacts.base.baseRoom.entity.User


@Entity(
    tableName = "report",
//    foreignKeys =
//    [ForeignKey(
//        entity = Geo::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("idGeoCheckIn"),
//        onUpdate = ForeignKey.CASCADE,
//        onDelete = ForeignKey.CASCADE
//    ), ForeignKey(
//        entity = ScheduleC::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("idSchedule"),
//        onUpdate = ForeignKey.CASCADE,
//        onDelete = ForeignKey.CASCADE
//    ), ForeignKey(
//        entity = Pdv::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("idSite"),
//        onUpdate = ForeignKey.CASCADE,
//        onDelete = ForeignKey.CASCADE
//    ), ForeignKey(
//        entity = User::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("iduser"),
//        onUpdate = ForeignKey.CASCADE,
//        onDelete = ForeignKey.CASCADE
//    )],
    primaryKeys = ["id"],
    indices = [Index("id", unique = true)]
)
data class Report(
    @ColumnInfo(name = "id") var id: String = "",

    @ColumnInfo(name = "iduser") var iduser: Int = -1,

    @ColumnInfo(name = "appversion") var appversion: String = "",

    @ColumnInfo(name = "timezone") var timezone: String = "",

    @ColumnInfo(name = "start") var start: Long = -1,

    @ColumnInfo(name = "end") var end: Long = -1,

    @ColumnInfo(name = "status") var status: Int = 0,

    @ColumnInfo(name = "idSchedule") var idSchedule: Int = 2,

    @ColumnInfo(name = "idGeoCheckIn") var idGeoCheckIn: Long = -1,

    @ColumnInfo(name = "idGeoCheckOut") var idGeoCheckOut: Long = -1,

    @ColumnInfo(name = "uuid") var uuid: Int = -1,

    @ColumnInfo(name = "address") var address: String = "",

    @ColumnInfo(name = "reportType") var reportType: Int = 0,

    @ColumnInfo(name = "fakeGps") var fakeGps: Int = 0,

    @ColumnInfo(name = "pdv") var pdv: String = "",

    @ColumnInfo(name = "idSite") var idSite: Int = 2
)