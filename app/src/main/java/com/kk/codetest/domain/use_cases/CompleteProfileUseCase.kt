package com.kk.codetest.domain.use_cases

import com.kk.codetest.data.utils.NetworkResource
import com.kk.codetest.domain.models.Profile
import com.kk.codetest.domain.repository.ProfileRepository

class CompleteProfileUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(): NetworkResource<Profile> {
        return profileRepository.completeProfile()
    }
}