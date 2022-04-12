package com.kk.codetest.di

import com.kk.codetest.domain.repository.ProfileRepository
import com.kk.codetest.domain.use_cases.CompleteProfileUseCase
import com.kk.codetest.domain.use_cases.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun profileCompleteProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(completeProfileUseCase = CompleteProfileUseCase(profileRepository))
    }
}