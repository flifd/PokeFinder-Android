package com.example.pokefinderandroid

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tv = TextView(this).apply {
            textSize = 16f
            text = "Loading Gen3 data..."
            setPadding(16, 16, 16, 16)
        }

        setContentView(tv)

        Thread {
            try {
                val hello = Gen3Bridge.getHelloGen3()
                val data = Gen3Bridge.getFirstStaticEncounter()
                runOnUiThread {
                    tv.text = "$hello\n$data"
                }
            } catch (e: Exception) {
                runOnUiThread {
                    tv.text = "Native call failed: ${e.message}"
                }
            }
        }.start()
    }
}