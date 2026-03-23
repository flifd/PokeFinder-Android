package com.example.pokefinderandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cv = R.layout.activity_main

        setContentView(cv)
    }

//    fun generateMon(view: View?) {
//        val mon = Gen3Bridge.getFirstStaticEncounter()
//        val textView = findViewById<View?>(R.id.monview) as TextView
//        textView.text = mon
//    }
}