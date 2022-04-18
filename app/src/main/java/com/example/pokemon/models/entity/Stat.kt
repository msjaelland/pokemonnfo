package com.example.pokemon.models.entity

import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.example.pokemon.R

data class Stat(
    val base_stat: Int? = null,
    val effort: Int? = null,
    val stat: NameAndUrl? = null
) {
    fun getIcon(resources: Resources): Drawable {
        return when (stat?.name) {
            StatHp -> resources.getDrawable(R.drawable.ic_heart_icon)
            StatAttack -> resources.getDrawable(R.drawable.ic_sword_icon)
            StatDefense -> resources.getDrawable(R.drawable.ic_shield_icon)
            StatSpecialAttack -> resources.getDrawable(R.drawable.ic_sword_icon)
            StatSpecialDefense -> resources.getDrawable(R.drawable.ic_shield_icon)
            Total -> resources.getDrawable(R.drawable.ic_sum_icon)
            else -> resources.getDrawable(R.drawable.ic_speed_icon)
        }
    }

    companion object {
        const val StatHp = "hp"
        const val StatAttack = "attack"
        const val StatDefense = "defense"
        const val StatSpecialAttack = "special-attack"
        const val StatSpecialDefense = "special-defense"
        const val StatSpeed = "speed"
        const val Total = "total"
    }
}