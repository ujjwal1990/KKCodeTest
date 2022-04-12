package com.kk.codetest.data.mappers

import com.kk.codetest.common.utils.Constants.Companion.EMPTY
import com.kk.codetest.data.remote.dtos.ProfileDTO
import com.kk.codetest.data.remote.dtos.ProfileDataDTO
import com.kk.codetest.domain.models.Profile
import com.kk.codetest.domain.models.ProfileData

fun ProfileDTO.toDomain(): Profile {
    return Profile(
        success = success ?: false,
        data = data?.toDomain() ?: ProfileData(EMPTY, EMPTY)
    )
}

fun ProfileDataDTO.toDomain(): ProfileData {
    return ProfileData(title = title ?: EMPTY, message = message ?: EMPTY)
}