package com.kk.codetest

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kk.codetest.domain.repository.ProfileRepository
import com.kk.codetest.domain.use_cases.CompleteProfileUseCase
import com.kk.codetest.domain.use_cases.ProfileUseCase
import com.kk.codetest.ui.menu.MenuViewModel
import com.kk.codetest.ui.menu.models.MenuCardType
import com.kk.codetest.ui.menu.models.MenuCardUiModel
import com.kk.codetest.ui.menu.models.MenuListItemType
import com.kk.codetest.ui.menu.profile.ProfileCardState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class MenuViewModelTest {

    @Rule
    @JvmField
    var mockRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var menuViewModel: MenuViewModel
    private val profileRepository: ProfileRepository = mock()
    private lateinit var profileUseCase: ProfileUseCase
    private lateinit var completeProfileUseCase: CompleteProfileUseCase

    @Mock
    private lateinit var resources: Resources


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        completeProfileUseCase = CompleteProfileUseCase(profileRepository)
        profileUseCase = ProfileUseCase(completeProfileUseCase)
        menuViewModel = MenuViewModel(profileUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadingState() {
        runBlocking {
            profileRepository.completeProfile()
            menuViewModel.profileCardState.observeForever {
                assertTrue(it is ProfileCardState.Loading)
            }
        }
    }

    @Test
    fun testSuccessState() {
        val profile = ProfileTestDataHelper.getProfileNetworkRes()
        runBlocking {
            whenever(profileRepository.completeProfile()).thenReturn(profile)
            menuViewModel.profileCardState.observeForever {
                assertTrue(it is ProfileCardState.Success)
            }
        }
    }

    @Test
    fun testErrorState() {
        runBlocking {
            whenever(profileRepository.completeProfile()).thenReturn(null)
            menuViewModel.profileCardState.observeForever {
                assertTrue(it is ProfileCardState.Error)
            }
        }
    }

    @Test
    fun testMenuListDataUiModelHasData() {
        assertEquals(menuViewModel.menuListDataUiModel.value.size, 4)
        //1st card
        assertEquals(
            MenuListItemType.MENU_CARD_ITEM,
            menuViewModel.menuListDataUiModel.value[0].menuListItemType
        )
        //2st card should be profile complete card
        assertEquals(
            MenuCardType.PROFILE_CARD,
            (menuViewModel.menuListDataUiModel.value[1].data as MenuCardUiModel).cardType
        )
        //last item
        assertEquals(
            MenuListItemType.MENU_BUTTON_ITEM,
            menuViewModel.menuListDataUiModel.value[3].menuListItemType
        )
    }

    @Test
    fun testMenuListDataUiModelHasNoData() {
        menuViewModel.menuListDataUiModel.value.clear()
        assertEquals(menuViewModel.menuListDataUiModel.value.size, 0)
    }

    @Test
    fun testCompleteProfileCardAfterAction() {
        val id = (menuViewModel.menuListDataUiModel.value[1].data as MenuCardUiModel).cardTitleId
        whenever(resources.getString(id)).thenReturn("COMPLETE YOUR\\nPROFILE")
        assertEquals("COMPLETE YOUR\\nPROFILE", resources.getString(id))
        val profile = ProfileTestDataHelper.getProfileNetworkRes()
        runBlocking {
            whenever(profileRepository.completeProfile()).thenReturn(profile)
            menuViewModel.profileCardState.observeForever {
                if (it is ProfileCardState.Success) {
                    assertEquals("Profile Completed", it.data?.data?.title)
                    assertNotEquals(resources.getString(id), it.data?.data?.title)
                }
            }
        }
    }
}
