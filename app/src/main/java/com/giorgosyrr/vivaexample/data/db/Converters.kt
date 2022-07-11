package com.giorgosyrr.vivaexample.data.db


import androidx.room.TypeConverter
import com.giorgosyrr.vivaexample.data.model.DataFromApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<DataFromApi>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DataFromApi>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<DataFromApi> {
        val gson = Gson()
        val type = object : TypeToken<List<DataFromApi>>() {}.type
        return gson.fromJson(value, type)
    }
}