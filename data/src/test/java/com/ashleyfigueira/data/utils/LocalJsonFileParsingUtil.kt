package com.ashleyfigueira.data.utils

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

object LocalJsonFileParsingUtil {

    @Throws(IOException::class)
    fun <T> localJsonFileToObject(type: Class<T>, fileName: String): T {
        val resource = LocalJsonFileParsingUtil::class.java.classLoader?.getResource(fileName)
        val f = File(resource!!.path)
        val reader = BufferedReader(FileReader(f))
        val gson = Gson()
        return gson.fromJson(reader, type)
    }
}