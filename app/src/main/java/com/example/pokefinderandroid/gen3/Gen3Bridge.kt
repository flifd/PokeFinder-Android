package com.example.pokefinderandroid.gen3

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Gen3Bridge {
    init {
        System.loadLibrary("pokefinder_jni")
    }
    external fun getValidAreas(
        feebasTile: Boolean,
        encounter: Int,
        profile: Profile3
    ): String

    fun getEncounterAreas(feebasTile: Boolean, encounter: Int, profile: Profile3): List<EncounterArea3> {
        val jsonString = getValidAreas(feebasTile, encounter, profile)

        val listType = object : TypeToken<List<EncounterArea3>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}