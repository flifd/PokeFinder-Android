package com.example.pokefinderandroid.gen3

import com.google.gson.annotations.SerializedName

enum class Game {
    None,
    @SerializedName("Ruby")
    Ruby,
    @SerializedName("Sapphire")
    Sapphire,
    RS,
    @SerializedName("Emerald")
    Emerald,
    RSE,
    @SerializedName("FireRed")
    FireRed,
    @SerializedName("LeafGreen")
    LeafGreen,
    FRLG,
    @SerializedName("Gales")
    Gales,
    @SerializedName("Colosseum")
    Colosseum,
    GC,
    Gen3
}

data class Profile3(
    val name: String,
    val version: Game,
    val tid: Int,
    val sid: Int,
    val deadBattery: Boolean
)
