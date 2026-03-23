package com.example.pokefinderandroid.gen3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pokefinderandroid.R

class Gen3MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gen3_main)
    }

    fun openToolView(view: View?) {
        val intent: Intent = when (view?.id) {
            R.id.buttonWild -> Intent(this, Gen3WildActivity::class.java)
//            R.id.buttonEgg -> Intent(this, Gen4MainActivity::class.java)  // Create this class if it doesn't exist
//            R.id.buttonGamecube -> Intent(this, Gen5MainActivity::class.java)  // Create this class if it doesn't exist
//            R.id.buttonIds -> Intent(this, Gen6MainActivity::class.java)  // Create this class if it doesn't exist
//            R.id.buttonStatic -> Intent(this, Gen7MainActivity::class.java)  // Create this class if it doesn't exist
            R.id.buttonProfile -> Intent(this, Gen3ProfileActivity::class.java)  // Create this class if it doesn't exist
            else -> return  // Do nothing if ID doesn't match
        }
        startActivity(intent)
    }
}