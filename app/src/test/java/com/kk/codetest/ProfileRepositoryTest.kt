package com.kk.codetest

import com.kk.codetest.data.mappers.toDomain
import com.kk.codetest.data.remote.api.ProfileApi
import com.kk.codetest.data.repository.ProfileRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProfileRepositoryTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val mockProfileApi: ProfileApi = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun repoReturnSuccess() {
        val profileDto = ProfileTestDataHelper.getProfileRes()
        runTest {
            whenever(mockProfileApi.completeProfile()).thenReturn(profileDto)
            val profileRepositoryImpl = ProfileRepositoryImpl(mockProfileApi)
            val profileData = profileRepositoryImpl.completeProfile()
            assertEquals(profileDto.body()?.toDomain(), profileData.data)
        }
    }

    @Test
    fun repoReturnFailure() {
        runTest {
            whenever(mockProfileApi.completeProfile()).thenReturn(null)
            val profileRepositoryImpl = ProfileRepositoryImpl(mockProfileApi)
            val profileData = profileRepositoryImpl.completeProfile()
            assertNull(profileData.errorMessage, null)
        }
    }
}
