package com.example.affirmations.data

import com.google.gson.Gson

import com.example.nasa_apod_app.model.GalleryInfo
import android.content.Context
import com.google.gson.reflect.TypeToken
import java.io.IOException

/**
 * [Datasource] generates a list of [GalleryInfo]
 */
class Datasource() {

    fun loadGalleryData(context: Context, name: String): List<GalleryInfo> {

        val jsonFileString = getJsonDataFromAsset(context, name)

        val gson = Gson()
        val listGalleryInfo = object : TypeToken<List<GalleryInfo>>() {}.type
//        val reqDta: List<GalleryInfo> = gson.fromJson(jsonFileString, listGalleryInfo)

        return gson.fromJson(jsonFileString, listGalleryInfo)
    }

    /**
     * Reading Json file from Assets
     * */
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}

