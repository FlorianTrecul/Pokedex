package com.floriantrecul.pokedex.util

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val message: Int?) : Resource<Nothing>()


    val extractData: T?
        get() = when (this) {
            is Loading -> null
            is Success -> data
            is Error -> null
        }
}