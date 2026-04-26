package com.example.pokefinderandroid.general

import com.example.pokefinderandroid.gen3.Encounter

open class EncounterArea() {
    open var location: Int = 0
    open var rate: Int = 0
    open var encounter: Encounter = Encounter.Grass
    open var pokemon: List<Slot> = listOf()

    constructor(location: Int, rate: Int, encounter: Encounter?, pokemon: List<Slot>) : this() {
        this.location = location
        this.rate = rate
        if (encounter != null) {
            this.encounter = encounter
        }
        this.pokemon = pokemon
    }
}
