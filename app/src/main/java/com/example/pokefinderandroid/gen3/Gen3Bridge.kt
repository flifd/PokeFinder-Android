package com.example.pokefinderandroid.gen3

object Gen3Bridge {
    init {
        System.loadLibrary("pokefinder_jni")
    }

    external fun getHelloGen3(): String
    external fun getFirstStaticEncounter(): String
}