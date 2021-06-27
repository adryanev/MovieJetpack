package dev.adryanev.dicoding.moviejetpack.data.local.converters

import androidx.room.TypeConverter

class CountryConverters {
    @TypeConverter
    fun listToString(values: List<String>): String {
        val strList = mutableListOf<String>()
        values.forEach {
            strList.add(it)
        }
        return strList.joinToString(",")
    }

    @TypeConverter
    fun stringToList(value: String): List<String> {
        val strList = mutableListOf<String>()
        value.split(",").forEach {
            strList.add(it)
        }
        return strList
    }
}