package com.gshp.desktop.framework.room

import android.annotation.SuppressLint
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

abstract class BaseDao<T>(val tableName: String, val primaryKey: String) {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertReplaceStrategy(obj:T) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract fun insertIgnoreStrategy(obj:T) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertReplaceStrategy(obj:List<T>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract fun insertIgnoreStrategy(obj:List<T>) : List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateReplaceStrategy(obj: T) : Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateIgnoreStrategy(obj: T) : Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateReplaceStrategy(obj:List<T>) : Int

    @Update
    abstract fun update(obj: T) : Int

    @Update
    abstract fun update(obj:List<T>) : Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun updateIgnoreStrategy(obj:List<T>) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract suspend fun insertSuspendReplaceStrategy(obj:T):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract suspend fun insertSuspendIgnoreStrategy(obj:T):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract suspend fun insertSuspendReplaceStrategy(obj:List<T>):List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract suspend fun insertSuspendIgnoreStrategy(obj:List<T>):List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateSuspendReplaceStrategy(obj: T):Int

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun updateSuspendIgnoreStrategy(obj: T):Int

    @Delete
    abstract suspend fun deleteSuspend(obj: T): Int

    @Delete
    abstract fun delete(obj: T)

    @Transaction
    open fun upsert(obj: T): Int {
        val id: Long = insertIgnoreStrategy(obj)
        return if (id == -1L) {
            updateIgnoreStrategy(obj)
        } else
            id.toInt()
    }

    @Transaction
    open fun upsert(objList: List<T>): List<Int> {
        val insertResult: List<Long> = insertIgnoreStrategy(objList)
        val updateList: MutableList<T> = ArrayList()
        for (i in insertResult.indices) {
            if (insertResult[i] == -1L) {
                updateList.add(objList[i])
            }
        }
       if (updateList.isNotEmpty())
            updateIgnoreStrategy(updateList)

        return insertResult.map { it.toInt() }
    }

    @RawQuery
    protected abstract fun executeQuery(query: SupportSQLiteQuery): List<T>?

    @RawQuery
    protected abstract fun deleteQuery(query: SupportSQLiteQuery) : Int

    @SuppressLint("RestrictedApi")
    open fun getAllLiveData(): LiveData<List<T>> {
        return object : ComputableLiveData<List<T>>() {
            private var observer: InvalidationTracker.Observer? = null
            @SuppressLint("RestrictedApi")
            override fun compute(): List<T> {
                if (observer == null) {
                    observer = object : InvalidationTracker.Observer(tableName) {
                        override fun onInvalidated(tables: Set<String>) = invalidate()
                    }
//                    BaseApplication.application.database.invalidationTracker
//                        .addWeakObserver(observer)
                }
                return getAll()
            }
        }.liveData
    }

    open fun getAll(): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName;")
        return executeQuery(query) ?: emptyList()
    }

    open fun deleteAll() : Int {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName")
        return deleteQuery(query)
    }

    open fun deleteByPrimaryKey(id: Any) : Int {
        return deleteByPrimaryKey(listOf(id))
    }

    open fun deleteByPrimaryKey(ids: List<Any>): Int {
        val result = StringBuilder()
        for (index in ids.indices) {
            if (index != 0) {
                result.append(",")
            }
            result.append("'").append(ids[index]).append("'")
        }
        val query = SimpleSQLiteQuery("DELETE FROM $tableName WHERE $primaryKey IN ($result);")

        return deleteQuery(query)
    }

    open fun getByField(value: Any, field: String): T?{
        return getByField(listOf(value),field).firstOrNull()
    }

    open suspend fun getByFieldSuspend(value: Any, field: String): T?{
        return getByField(listOf(value),field).firstOrNull()
    }

    @SuppressLint("RestrictedApi")
    open fun getByFieldLiveData(value: Any, field: String): LiveData<T> {
        val resultLiveData = MediatorLiveData<T>()
        resultLiveData.addSource(getByFieldLiveData(listOf(value),field)) { obj ->
            resultLiveData.postValue(obj.firstOrNull())
        }
        return resultLiveData
    }

    @SuppressLint("RestrictedApi")
    open fun getByFieldLiveData(values: List<Any>, field: String): LiveData<List<T>> {
        return object : ComputableLiveData<List<T>>() {
            private var observer: InvalidationTracker.Observer? = null
            @SuppressLint("RestrictedApi")
            override fun compute(): List<T> {
                if (observer == null) {
                    observer = object : InvalidationTracker.Observer(tableName) {
                        override fun onInvalidated(tables: Set<String>) = invalidate()
                    }
//                    BaseApplication.application.database.invalidationTracker.addWeakObserver(observer)
                }
                return getByField(values,field)
            }
        }.liveData
    }

    open fun getByPrimaryKey(id: Any): T? {
        return getByPrimaryKey(listOf(id)).firstOrNull()
    }

    open suspend fun getByPrimaryKeySuspend(id: Any): T? {
        return getByPrimaryKey(listOf(id)).firstOrNull()
    }

    @SuppressLint("RestrictedApi")
    open fun getByPrimaryKeyLiveData(id: Any): LiveData<T> {
        val resultLiveData = MediatorLiveData<T>()
        resultLiveData.addSource(getByPrimaryKeyLiveData(listOf(id))) { obj ->
            resultLiveData.postValue(obj.firstOrNull())
        }
        return resultLiveData
    }

    @SuppressLint("RestrictedApi")
    open fun getByPrimaryKeyLiveData(ids: List<Any>): LiveData<List<T>> {
        return object : ComputableLiveData<List<T>>() {
            private var observer: InvalidationTracker.Observer? = null
            @SuppressLint("RestrictedApi")
            override fun compute(): List<T> {
                if (observer == null) {
                    observer = object : InvalidationTracker.Observer(tableName) {
                        override fun onInvalidated(tables: Set<String>) = invalidate()
                    }
//                    BaseApplication.application.database.invalidationTracker.addWeakObserver(observer)
                }
                return getByPrimaryKey(ids)
            }
        }.liveData
    }

    open fun getByPrimaryKey(ids: List<Any>): List<T> {
        val result = StringBuilder()
        for (index in ids.indices) {
            if (index != 0) {
                result.append(",")
            }
            result.append("'").append(ids[index]).append("'")
        }
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE $primaryKey IN ($result);")

        return executeQuery(query) ?: emptyList()
    }

    open fun getByField(values: List<Any>,field: String): List<T> {
        val result = StringBuilder()
        for (index in values.indices) {
            if (index != 0) {
                result.append(",")
            }
            result.append("'").append(values[index]).append("'")
        }
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE $field IN ($result);")

        return executeQuery(query) ?: emptyList()
    }

    open suspend fun getByPrimaryKeySuspend(ids: List<Any>): List<T> {
        val result = StringBuilder()
        for (index in ids.indices) {
            if (index != 0) {
                result.append(",")
            }
            result.append("'").append(ids[index]).append("'")
        }
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE $primaryKey IN ($result);")

        return executeQuery(query) ?: emptyList()
    }
}