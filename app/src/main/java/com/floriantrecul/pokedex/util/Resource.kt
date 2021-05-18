package com.floriantrecul.pokedex.util

sealed class Resource<out T : Any> {
    class Empty<T : Any> : Resource<T>()
    class Loading<T : Any> : Resource<T>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val message: Int?) : Resource<Nothing>()

    val extractData: T?
        get() = when (this) {
            is Empty -> null
            is Loading -> null
            is Success -> data
            is Error -> null
        }
}