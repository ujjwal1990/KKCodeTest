package com.kk.codetest.data.remote.api

import com.kk.codetest.data.remote.dtos.ProfileDTO
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {
    @GET("hello")
    suspend fun completeProfile(): Response<ProfileDTO>
}