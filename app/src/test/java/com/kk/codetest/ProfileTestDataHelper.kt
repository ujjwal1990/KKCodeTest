package com.kk.codetest

import com.kk.codetest.data.mappers.toDomain
import com.kk.codetest.data.remote.dtos.ProfileDTO
import com.kk.codetest.data.remote.dtos.ProfileDataDTO
import com.kk.codetest.data.utils.NetworkResource
import com.kk.codetest.domain.models.Profile
import retrofit2.Response

object ProfileTestDataHelper {
    fun getProfile(): ProfileDTO {
        return ProfileDTO(success = true, data = getProfileData())
    }

    fun getProfileData(): ProfileDataDTO {
        return ProfileDataDTO(
            title = "Profile Completed", message = "Your profile looks great!"
        )
    }

    fun getProfileRes(): Response<ProfileDTO> {
        return Response.success(ProfileDTO(success = true, data = getProfileData()))
    }

    fun getProfileNetworkRes(): NetworkResource<Profile> {
        return NetworkResource.Success(getProfile().toDomain())
    }
}