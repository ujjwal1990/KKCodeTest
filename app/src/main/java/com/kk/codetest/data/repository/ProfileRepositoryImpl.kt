package com.kk.codetest.data.repository

import com.kk.codetest.data.mappers.toDomain
import com.kk.codetest.data.remote.api.ProfileApi
import com.kk.codetest.data.utils.NetworkResource
import com.kk.codetest.domain.models.Profile
import com.kk.codetest.domain.repository.ProfileRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val profileApi: ProfileApi) :
    ProfileRepository {
    override suspend fun completeProfile(): NetworkResource<Profile> {
        return try {
            val response = profileApi.completeProfile()
            if (response.isSuccessful) {
                NetworkResource.Success(response.body()?.toDomain())
            } else {
                response.errorBody()?.let { msg ->
                    NetworkResource.Error(msg.toString())
                } ?: NetworkResource.Error("Unknown error")
            }
        } catch (e: NullPointerException) {
            NetworkResource.Error("Error in Response")
        } catch (e: IOException) {
            NetworkResource.Error("Oops! Couldn't reach server. Check your internet connection.")
        } catch (e: HttpException) {
            NetworkResource.Error("Oops! Something went wrong. Please try again.")
        }
    }
}