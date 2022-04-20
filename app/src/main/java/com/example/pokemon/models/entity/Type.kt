package com.example.pokemon.models.entity

import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import com.example.pokemon.R

data class Type(
    val slot: Int? = null,
    val type: NameAndUrl? = null
) {
    fun getColour(resources: Resources): Int {
        return when (type?.name) {
            Normal -> ResourcesCompat.getColor(resources, R.color.normal, null)
            Fire -> ResourcesCompat.getColor(resources, R.color.fire, null)
            Water -> ResourcesCompat.getColor(resources, R.color.water, null)
            Electric -> ResourcesCompat.getColor(resources, R.color.electric, null)
            Grass -> ResourcesCompat.getColor(resources, R.color.grass, null)
            Ice -> ResourcesCompat.getColor(resources, R.color.ice, null)
            Fighting -> ResourcesCompat.getColor(resources, R.color.fighting, null)
            Poison -> ResourcesCompat.getColor(resources, R.color.poison, null)
            Ground -> ResourcesCompat.getColor(resources, R.color.ground, null)
            Flying -> ResourcesCompat.getColor(resources, R.color.flying, null)
            Psychic -> ResourcesCompat.getColor(resources, R.color.psychic, null)
            Bug -> ResourcesCompat.getColor(resources, R.color.bug, null)
            Rock -> ResourcesCompat.getColor(resources, R.color.rock, null)
            Ghost -> ResourcesCompat.getColor(resources, R.color.ghost, null)
            Dragon -> ResourcesCompat.getColor(resources, R.color.dragon, null)
            Dark -> ResourcesCompat.getColor(resources, R.color.dark, null)
            Steel -> ResourcesCompat.getColor(resources, R.color.steel, null)
            Fairy -> ResourcesCompat.getColor(resources, R.color.fairy, null)
            else -> ResourcesCompat.getColor(resources, R.color.normal, null)
        }
    }

    companion object {
        const val Normal = "normal"
        const val Fire = "fire"
        const val Water = "water"
        const val Electric = "electric"
        const val Grass = "grass"
        const val Ice = "ice"
        const val Fighting = "fighting"
        const val Poison = "poison"
        const val Ground = "ground"
        const val Flying = "flying"
        const val Psychic = "psychic"
        const val Bug = "bug"
        const val Rock = "rock"
        const val Ghost = "ghost"
        const val Dragon = "dragon"
        const val Dark = "dark"
        const val Steel = "steel"
        const val Fairy = "fairy"
    }
}

