package com.example.pokefinderandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cv = R.layout.activity_main

        setContentView(cv)
    }

    fun openGenView(view: View?) {

        val intent = Intent(this, Gen3MainActivity::class.java)
        startActivity(intent)
    }

//    fun generateMon(view: View?) {
//        val mon = Gen3Bridge.getFirstStaticEncounter()
//        val textView = findViewById<View?>(R.id.monview) as TextView
//        textView.text = mon
//    }
}