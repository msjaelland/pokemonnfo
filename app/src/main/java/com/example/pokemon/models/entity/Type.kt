package com.example.pokemon.models.entity

import android.content.res.Resources
import com.example.pokemon.R

data class Type(
    val slot: Int? = null,
    val type: NameAndUrl? = null
) {
    fun getColour(resources: Resources): Int {
        return when (type?.name) {
            Normal -> resources.getColor(R.color.normal)
            Fire -> resources.getColor(R.color.fire)
            Water -> resources.getColor(R.color.water)
            Electric -> resources.getColor(R.color.electric)
            Grass -> resources.getColor(R.color.grass)
            Ice -> resources.getColor(R.color.ice)
            Fighting -> resources.getColor(R.color.fighting)
            Poison -> resources.getColor(R.color.poison)
            Ground -> resources.getColor(R.color.ground)
            Flying -> resources.getColor(R.color.flying)
            Psychic -> resources.getColor(R.color.psychic)
            Bug -> resources.getColor(R.color.bug)
            Rock -> resources.getColor(R.color.rock)
            Ghost -> resources.getColor(R.color.ghost)
            Dragon -> resources.getColor(R.color.dragon)
            Dark -> resources.getColor(R.color.dark)
            Steel -> resources.getColor(R.color.steel)
            Fairy -> resources.getColor(R.color.fairy)
            else -> resources.getColor(R.color.normal)
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

