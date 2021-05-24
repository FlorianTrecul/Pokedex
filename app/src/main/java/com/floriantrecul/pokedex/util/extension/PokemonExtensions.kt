package com.floriantrecul.pokedex.util.extension

import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.data.api.network.model.PokemonAbilityDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonFlavorTextDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonMoveDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonStatDto
import com.floriantrecul.pokedex.data.model.PokemonAbility
import com.floriantrecul.pokedex.data.model.PokemonMove
import com.floriantrecul.pokedex.data.model.PokemonStat
import com.floriantrecul.pokedex.data.model.PokemonType

fun PokemonAbilityDto.toPokemonAbility(): PokemonAbility = PokemonAbility(
    name = this.ability.name
)

fun PokemonMoveDto.toPokemonMove(): PokemonMove = PokemonMove(
    name = this.move.name,
)

fun PokemonStatDto.toPokemonStat(): PokemonStat = PokemonStat(
    name = this.stat.name,
    value = this.baseStat
)

fun String.toPokemonType(): PokemonType =
    PokemonType.values().find { it.value == this } ?: PokemonType.UNKNOWN

fun List<PokemonFlavorTextDto>.getPokemonDescription(): String =
    find { it.language.name == "en" }!!.flavorText

fun String.extractPokemonId(): Int {
    return if (this.endsWith("/")) {
        this.dropLast(1).takeLastWhile { it.isDigit() }.toInt()
    } else {
        this.takeLastWhile { it.isDigit() }.toInt()
    }
}

fun PokemonType.getMainColor(): Int {
    return when (this) {
        PokemonType.NORMAL -> R.color.colorNormal
        PokemonType.FIRE -> R.color.colorFire
        PokemonType.WATER -> R.color.colorWater
        PokemonType.ELECTRIC -> R.color.colorElectric
        PokemonType.GRASS -> R.color.colorGrass
        PokemonType.ICE -> R.color.colorIce
        PokemonType.FIGHTING -> R.color.colorFighting
        PokemonType.POISON -> R.color.colorPoison
        PokemonType.GROUND -> R.color.colorGround
        PokemonType.FLYING -> R.color.colorFlying
        PokemonType.PSYCHIC -> R.color.colorPsychic
        PokemonType.BUG -> R.color.colorBug
        PokemonType.ROCK -> R.color.colorRock
        PokemonType.GHOST -> R.color.colorGhost
        PokemonType.DRAGON -> R.color.colorDragon
        PokemonType.DARK -> R.color.colorDark
        PokemonType.STEEL -> R.color.colorSteel
        PokemonType.FAIRY -> R.color.colorFairy
        PokemonType.UNKNOWN -> R.color.colorNormal
    }
}

fun PokemonType.getTypeIcon(): Int {
    return when (this) {
        PokemonType.NORMAL -> R.drawable.ic_normal_type
        PokemonType.FIRE -> R.drawable.ic_fire_type
        PokemonType.WATER -> R.drawable.ic_water_type
        PokemonType.ELECTRIC -> R.drawable.ic_electric_type
        PokemonType.GRASS -> R.drawable.ic_grass_type
        PokemonType.ICE -> R.drawable.ic_ice_type
        PokemonType.FIGHTING -> R.drawable.ic_fight_type
        PokemonType.POISON -> R.drawable.ic_poison_type
        PokemonType.GROUND -> R.drawable.ic_ground_type
        PokemonType.FLYING -> R.drawable.ic_flying_type
        PokemonType.PSYCHIC -> R.drawable.ic_psychic_type
        PokemonType.BUG -> R.drawable.ic_bug_type
        PokemonType.ROCK -> R.drawable.ic_rock_type
        PokemonType.GHOST -> R.drawable.ic_ghost_type
        PokemonType.DRAGON -> R.drawable.ic_dragon_type
        PokemonType.DARK -> R.drawable.ic_dark_type
        PokemonType.STEEL -> R.drawable.ic_steel_type
        PokemonType.FAIRY -> R.drawable.ic_fairy_type
        PokemonType.UNKNOWN -> R.drawable.ic_normal_type
    }
}

fun PokemonType.getTypeTagIcon(): Int {
    return when (this) {
        PokemonType.NORMAL -> R.drawable.ic_tag_normal_type
        PokemonType.FIRE -> R.drawable.ic_tag_fire_type
        PokemonType.WATER -> R.drawable.ic_tag_water_type
        PokemonType.ELECTRIC -> R.drawable.ic_tag_electric_type
        PokemonType.GRASS -> R.drawable.ic_tag_grass_type
        PokemonType.ICE -> R.drawable.ic_tag_ice_type
        PokemonType.FIGHTING -> R.drawable.ic_tag_fight_type
        PokemonType.POISON -> R.drawable.ic_tag_poison_type
        PokemonType.GROUND -> R.drawable.ic_tag_ground_type
        PokemonType.FLYING -> R.drawable.ic_tag_flying_type
        PokemonType.PSYCHIC -> R.drawable.ic_tag_psychic_type
        PokemonType.BUG -> R.drawable.ic_tag_bug_type
        PokemonType.ROCK -> R.drawable.ic_tag_rock_type
        PokemonType.GHOST -> R.drawable.ic_tag_ghost_type
        PokemonType.DRAGON -> R.drawable.ic_tag_dragon_type
        PokemonType.DARK -> R.drawable.ic_tag_dark_type
        PokemonType.STEEL -> R.drawable.ic_tag_steel_type
        PokemonType.FAIRY -> R.drawable.ic_tag_fairy_type
        PokemonType.UNKNOWN -> R.drawable.ic_tag_normal_type
    }
}

fun String.toBaseStatResId(): Int = when (this) {
    "hp" -> R.string.tab_base_stats_label_hp
    "attack" -> R.string.tab_base_stats_label_attack
    "defense" -> R.string.tab_base_stats_label_defense
    "special-attack" -> R.string.tab_base_stats_label_sp_attack
    "special-defense" -> R.string.tab_base_stats_label_sp_defense
    "speed" -> R.string.tab_base_stats_label_speed
    else -> R.string.error_generic
}

fun Int.getStatColor(): Int {
    return when {
        this >= 100 -> {
            R.color.colorStatMaximum
        }
        this in 99 downTo 60 -> {
            R.color.colorStatMedium
        }
        else -> {
            R.color.colorStatMinimum
        }
    }
}