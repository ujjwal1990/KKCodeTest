package com.kk.codetest.domain.repository

import com.kk.codetest.data.utils.NetworkResource
import com.kk.codetest.domain.models.Profile

interface ProfileRepository {
    suspend fun completeProfile(): NetworkResource<Profile>
}