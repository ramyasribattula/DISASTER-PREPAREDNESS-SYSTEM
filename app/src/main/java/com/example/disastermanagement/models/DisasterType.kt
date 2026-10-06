package com.example.disastermanagement.models

import java.io.Serializable

enum class DisasterType(val displayName: String, val description: String) : Serializable {
    EARTHQUAKE("Earthquake", "Ground shaking caused by tectonic movements"),
    FLOOD("Flood", "Overflow of water onto normally dry land"),
    FIRE("Fire", "Rapid oxidation producing heat and light"),
    CYCLONE("Cyclone", "Rotating storm system with low pressure center"),
    LANDSLIDE("Landslide", "Downward movement of rock, earth, or debris"),
    DROUGHT("Drought", "Extended period of below-average precipitation"),
    TSUNAMI("Tsunami", "Series of waves caused by underwater disturbances")
}

