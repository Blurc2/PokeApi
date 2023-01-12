package com.gshp.desktop.framework.room.user

import androidx.room.Dao
import com.gshp.desktop.framework.room.BaseDao

@Dao
abstract class UserDao: BaseDao<User>("user","id") {

}