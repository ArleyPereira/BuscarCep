package com.br.buscarcep.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> sucess(data: T): Resource<T> =
            Resource(status = Status.SUCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)

    }
}