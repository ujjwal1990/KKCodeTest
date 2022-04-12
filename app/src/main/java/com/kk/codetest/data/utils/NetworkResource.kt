package com.kk.codetest.data.utils

typealias SimpleResource = NetworkResource<Unit>

sealed class NetworkResource<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?) : NetworkResource<T>(data)
    class Error<T>(error: String, data: T? = null) : NetworkResource<T>(data, error)
}
