package com.kk.codetest.common.utils

sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T>(data: T?) : Resource<T>()
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(errorMessage: String?) : Resource<T>(errorMessage = errorMessage)
}