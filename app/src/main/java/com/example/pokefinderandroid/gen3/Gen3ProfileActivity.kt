package com.example.pokefinderandroid.gen3

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokefinderandroid.R
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Gen3ProfileActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProfileAdapter
    private val profiles = mutableListOf<Profile3>()
    private val gson = Gson()
    private val prefsKey = "gen3_profiles"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gen3_profile)

        recyclerView = findViewById(R.id.recyclerViewProfiles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProfileAdapter(profiles, ::onEditProfile, ::onDeleteProfile)
        recyclerView.adapter = adapter

        val buttonAdd = findViewById<FloatingActionButton>(R.id.buttonAddProfile)
        buttonAdd.setOnClickListener { showProfileDialog(null) }

        loadProfiles()
    }

    private fun loadProfiles() {
        val prefs = getSharedPreferences("pokefinder", Context.MODE_PRIVATE)
        val json = prefs.getString(prefsKey, null)
        if (json != null) {
            val type = object : TypeToken<List<Profile3>>() {}.type
            val loadedProfiles: List<Profile3> = gson.fromJson(json, type)
            profiles.clear()
            profiles.addAll(loadedProfiles)
            adapter.notifyDataSetChanged()
        }
    }

    private fun saveProfiles() {
        val prefs = getSharedPreferences("pokefinder", Context.MODE_PRIVATE)
        val json = gson.toJson(profiles)
        prefs.edit().putString(prefsKey, json).apply()
    }

    private fun showProfileDialog(profile: Profile3?, position: Int? = null) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_profile, null)
        val editTextName = dialogView.findViewById<TextInputEditText>(R.id.editTextName)
        val spinnerVersion = dialogView.findViewById<Spinner>(R.id.spinnerVersion)
        val editTextTid = dialogView.findViewById<TextInputEditText>(R.id.editTextTid)
        val editTextSid = dialogView.findViewById<TextInputEditText>(R.id.editTextSid)
        val checkBoxDeadBattery = dialogView.findViewById<MaterialCheckBox>(R.id.checkBoxDeadBattery)

        val versions = Game.values().map { it.name }
        val versionAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, versions)
        versionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerVersion.adapter = versionAdapter

        if (profile != null) {
            editTextName.setText(profile.name)
            spinnerVersion.setSelection(versions.indexOf(profile.version.name))
            editTextTid.setText(profile.tid.toString())
            editTextSid.setText(profile.sid.toString())
            checkBoxDeadBattery.isChecked = profile.deadBattery
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle(if (profile == null) "Add Profile" else "Edit Profile")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val name = editTextName.text.toString()
                val versionStr = spinnerVersion.selectedItem.toString()
                val tid = editTextTid.text.toString().toIntOrNull() ?: 0
                val sid = editTextSid.text.toString().toIntOrNull() ?: 0
                val deadBattery = checkBoxDeadBattery.isChecked

                val version = Game.valueOf(versionStr)
                val newProfile = Profile3(name, version, tid, sid, deadBattery)

                if (position != null) {
                    profiles[position] = newProfile
                } else {
                    profiles.add(newProfile)
                }
                adapter.notifyDataSetChanged()
                saveProfiles()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun onEditProfile(position: Int) {
        val profile = profiles[position]
        showProfileDialog(profile, position)
    }

    private fun onDeleteProfile(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete Profile")
            .setMessage("Are you sure you want to delete this profile?")
            .setPositiveButton("Delete") { _, _ ->
                profiles.removeAt(position)
                adapter.notifyItemRemoved(position)
                saveProfiles()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}