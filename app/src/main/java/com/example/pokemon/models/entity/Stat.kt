package com.example.pokemon.models.entity

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.example.pokemon.R

data class Stat(
    val base_stat: Int? = null,
    val effort: Int? = null,
    val stat: NameAndUrl? = null
) {
    fun getIcon(resources: Resources): Drawable? {
        return when (stat?.name) {
            StatHp -> ResourcesCompat.getDrawable(resources, R.drawable.ic_heart_icon, null)
            StatAttack -> ResourcesCompat.getDrawable(resources, R.drawable.ic_sword_icon, null)
            StatDefense -> ResourcesCompat.getDrawable(resources, R.drawable.ic_shield_icon, null)
            StatSpecialAttack -> ResourcesCompat.getDrawable(resources, R.drawable.ic_sword_icon, null)
            StatSpecialDefense -> ResourcesCompat.getDrawable(resources, R.drawable.ic_shield_icon, null)
            else -> ResourcesCompat.getDrawable(resources, R.drawable.ic_speed_icon, null)
        }
    }

    companion object {
        const val StatHp = "hp"
        const val StatAttack = "attack"
        const val StatDefense = "defense"
        const val StatSpecialAttack = "special-attack"
        const val StatSpecialDefense = "special-defense"
        const val StatSpeed = "speed"
    }
}