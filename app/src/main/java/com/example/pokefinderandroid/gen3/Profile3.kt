package com.example.pokefinderandroid.gen3

import com.google.gson.annotations.SerializedName

enum class Game {
    @SerializedName("Ruby")
    Ruby,
    @SerializedName("Sapphire")
    Sapphire,
    @SerializedName("Emerald")
    Emerald,
    @SerializedName("FireRed")
    FireRed,
    @SerializedName("LeafGreen")
    LeafGreen,
    @SerializedName("Gales")
    Gales,
    @SerializedName("Colosseum")
    Colosseum
}

data class Profile3(
    val name: String,
    val version: Game,
    val tid: Int,
    val sid: Int,
    val deadBattery: Boolean
)
