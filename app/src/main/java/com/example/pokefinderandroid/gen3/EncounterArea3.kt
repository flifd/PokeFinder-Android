package com.example.pokefinderandroid.gen3

import com.example.pokefinderandroid.general.EncounterArea
import com.example.pokefinderandroid.general.Slot

// In EncounterArea3.kt
data class EncounterArea3(
    var location: Int,
    var rate: Int,
    var encounter: Encounter, // Representing the Encounter enum as Int for simplicity in JNI
    var pokemon: List<Slot>
)
{
}