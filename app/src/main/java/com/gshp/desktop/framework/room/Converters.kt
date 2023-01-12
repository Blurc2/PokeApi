package com.gshp.desktop.framework.room

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import com.cgsa.artifacts.base.baseRoom.entity.AnswerPrefilledSection
import com.cgsa.artifacts.base.baseRoom.entity.InfoInput
import com.cgsa.artifacts.data.dto.firebase.Site
import com.cgsa.artifacts.dtos.ChatAttachmentDto
import com.google.gson.reflect.TypeToken
import com.raer.utils.fromJson
import com.raer.utils.toArrayList
import com.raer.utils.toJson
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Converters {

    @TypeConverter
    fun listPrefilledToString(lst: Array<AnswerPrefilledSection>): String {
        return lst.toJson()
    }
    @TypeConverter
    fun PrefilledToString(string: String): Array<AnswerPrefilledSection> {
        return string.fromJson()
    }

    @TypeConverter
    fun listSiteToString(lst: List<Site>): String {
        return lst.toJson()
    }
    @TypeConverter
    fun SiteToString(string: String): List<Site> {
        return string.fromJson(object : TypeToken<List<Site>>() {}.type)
    }

    @TypeConverter
    fun listToString(lst: List<String>): String {
        return lst.toString()
    }

    @TypeConverter
    fun lstInfo(lst: HashMap<Int,ArrayList<InfoInput>?>):String {
        return lst.toJson()
    }
    @TypeConverter
    @SuppressLint("UseSparseArrays")
    fun  getLstInfo(string: String) :HashMap<Int,ArrayList<InfoInput>>{
        val map = HashMap<Int,ArrayList<InfoInput>>()
        val auxMap = string.fromJson<HashMap<String,ArrayList<InfoInput>>>(object : TypeToken<HashMap<String, ArrayList<InfoInput>>>() {}.type)
        auxMap.keys.forEach {
            auxMap[it]?.let {lst ->
                map[it.toInt()] = ArrayList(lst)
            }
        }
        return map
    }


    @TypeConverter
    fun strignToList(string: String): List<String> {
        return string.replace("[", "").replace("]", "").replace(", ", ",").split(",")
    }

    @TypeConverter
    fun intlistToString(lst: List<Int>): String {
        return lst.toString()
    }

    @TypeConverter
    fun strignToListint(string: String): List<Int> {
        return string.replace("[", "").replace("]", "").replace(", ", ",").split(",").let {
            if(it.size == 1 && it[0].isEmpty())
                emptyList()
            else
                it.map {
                    it.toInt()
                }
        }
    }

    @TypeConverter
    fun arraylistToString(lst: ArrayList<String>): String {
        return lst.toString()
    }


    @TypeConverter
    fun strignToArrayList(string: String): ArrayList<String> {
        return string.replace("[", "").replace("]", "").replace(", ", ",").split(",").toArrayList()
    }

    @TypeConverter
    fun arrayListChatAttachmentDtoToString(lst: ArrayList<ChatAttachmentDto>): String {
        return lst.toJson()
    }


    @TypeConverter
    fun stringToArrayListChatAttachmentDto(string: String): ArrayList<ChatAttachmentDto> {
        val listType: Type = object : TypeToken<ArrayList<ChatAttachmentDto>>() {}.type
        return string.fromJson(listType)
    }


    @TypeConverter
    fun intarraylistToString(lst: ArrayList<Int>): String {
        return lst.toString()
    }

    @TypeConverter
    fun strignToArrayListint(string: String): ArrayList<Int> {
        return string.replace("[", "").replace("]", "").replace(", ", ",").split(",").let {
            if(it.size == 1 && it[0].isEmpty())
                emptyList()
            else
                it.map {
                    it.toInt()
                }
        }.toArrayList()
    }

    @TypeConverter
    fun hashMapToString(lst: HashMap<Int,ArrayList<Int>?>): String {
        return lst.toJson()
    }
    @SuppressLint("UseSparseArrays")
    @TypeConverter
    fun stringToHashMap(string: String): HashMap<Int, ArrayList<Int>> {
        val map = HashMap<Int,ArrayList<Int>>()
        val auxMap = string.fromJson<HashMap<String,ArrayList<Int>>>()
        auxMap.keys.forEach {
            auxMap[it]?.let {lst ->
                map[it.toInt()] = ArrayList(lst)
            }
        }
        return map
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}