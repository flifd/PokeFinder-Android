package com.example.pokefinderandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pokefinderandroid.gen3.Gen3MainActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cv = R.layout.activity_main

        setContentView(cv)
    }

    fun openGenView(view: View?) {
        val intent: Intent = when (view?.id) {
            R.id.buttonGen3 -> Intent(this, Gen3MainActivity::class.java)
//            R.id.buttonGen4 -> Intent(this, Gen4MainActivity::class.java)  // Create this class if it doesn't exist
//            R.id.buttonGen5 -> Intent(this, Gen5MainActivity::class.java)  // Create this class if it doesn't exist
//            R.id.buttonGen6 -> Intent(this, Gen6MainActivity::class.java)  // Create this class if it doesn't exist
//            R.id.buttonGen7 -> Intent(this, Gen7MainActivity::class.java)  // Create this class if it doesn't exist
//            R.id.buttonGen8 -> Intent(this, Gen8MainActivity::class.java)  // Create this class if it doesn't exist
            else -> return  // Do nothing if ID doesn't match
        }

        startActivity(intent)
    }

//    fun generateMon(view: View?) {
//        val mon = Gen3Bridge.getFirstStaticEncounter()
//        val textView = findViewById<View?>(R.id.monview) as TextView
//        textView.text = mon
//    }
}