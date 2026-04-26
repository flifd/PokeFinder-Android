package com.example.pokefinderandroid.gen3

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pokefinderandroid.R

class Gen3WildActivity : AppCompatActivity() {

    private lateinit var spinnerEncounter: Spinner
    private lateinit var checkBoxFeebas: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gen3_wild)

        spinnerEncounter = findViewById(R.id.spinnerEncounter)
        checkBoxFeebas = findViewById(R.id.checkBoxFeebas)

        // Populate Spinner with Encounter enum names
        val encounterValues = Encounter.values().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, encounterValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEncounter.adapter = adapter

        // Set the Listener to trigger logic immediately upon selection
        spinnerEncounter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // This view parameter might be null, so we pass the spinner or null to our function
                onGetEncounterClick(spinnerEncounter)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        checkBoxFeebas.setOnCheckedChangeListener { _, _ ->
            onGetEncounterClick(checkBoxFeebas)
        }
    }

    fun onGetEncounterClick(view: View) {
        // Get Feebas value from the tickbox
        val feebasTile = checkBoxFeebas.isChecked
        // Get the Encounter value from the spinner
        val encounter = spinnerEncounter.selectedItemPosition

        // TODO: implement proper profile selection
        val profile = Profile3("Tavi", Game.Emerald, 19001, 35664, true)

        val result = generateWildEncounters(null, feebasTile, encounter, profile)
    }

    fun generateWildEncounters(view: View?, feebasTile: Boolean, encounter: Int, profile: Profile3): List<EncounterArea3> {
        val test = Gen3Bridge.getEncounterAreas(feebasTile, encounter, profile)
        return test
    }
}