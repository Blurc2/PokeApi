package com.gshp.desktop.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gshp.desktop.BuildConfig
import com.gshp.desktop.framework.room.form.Form
import com.gshp.desktop.framework.room.form.FormDao
import com.gshp.desktop.framework.room.input.Input
import com.gshp.desktop.framework.room.input.InputDao
import com.gshp.desktop.framework.room.module.Module
import com.gshp.desktop.framework.room.module.ModuleDao
import com.gshp.desktop.framework.room.option.Option
import com.gshp.desktop.framework.room.option.OptionDao
import com.gshp.desktop.framework.room.report.Report
import com.gshp.desktop.framework.room.report.ReportDao
import com.gshp.desktop.framework.room.section.Section
import com.gshp.desktop.framework.room.section.SectionDao
import com.gshp.desktop.framework.room.user.User
import com.gshp.desktop.framework.room.user.UserDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

private const val DATABASE_NAME = "DesktopDB"
private const val ENCRYPT_KEY = "DesktopDB"
private const val CURRENT_VERSION = 1

@Database(entities = [User::class, Report::class, Module::class, Form::class, Section::class, Input::class, Option::class], version = CURRENT_VERSION)
@TypeConverters(Converters::class)
abstract class DesktopDatabase : RoomDatabase() {

//    abstract fun counterDao(): CounterDao
    abstract fun userDao(): UserDao
    abstract fun reportDao(): ReportDao
    abstract fun moduleDao(): ModuleDao
    abstract fun formDao(): FormDao
    abstract fun sectionDao(): SectionDao
    abstract fun inputDao(): InputDao
    abstract fun optionDao(): OptionDao

    companion object {
        @Volatile
        private var INSTANCE: DesktopDatabase? = null

        fun getInstance(context: Context): DesktopDatabase {
            val tempInstance = INSTANCE

            return tempInstance ?: run {
                synchronized(this) {
                    val factory = SupportFactory(SQLiteDatabase.getBytes(ENCRYPT_KEY.toCharArray()))
                    val builder = Room.databaseBuilder(context, DesktopDatabase::class.java, DATABASE_NAME)

                    if (BuildConfig.DEBUG) builder.fallbackToDestructiveMigration()
                    else builder.openHelperFactory(factory)

                    builder.build().also { INSTANCE = it }
                }
            }
        }
    }
}