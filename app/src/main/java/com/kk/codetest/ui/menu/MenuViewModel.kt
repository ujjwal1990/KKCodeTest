package com.kk.codetest.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kk.codetest.R
import com.kk.codetest.common.utils.Constants.Companion.EMPTY
import com.kk.codetest.data.utils.NetworkResource
import com.kk.codetest.domain.use_cases.ProfileUseCase
import com.kk.codetest.ui.menu.models.MenuCardType
import com.kk.codetest.ui.menu.models.MenuCardUiModel
import com.kk.codetest.ui.menu.models.MenuListDataUiModel
import com.kk.codetest.ui.menu.models.MenuListItemType
import com.kk.codetest.ui.menu.profile.ProfileCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PROFILE_CARD_INDEX = 1

@HiltViewModel
class MenuViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) : ViewModel() {
    var menuBarVisibility: Boolean = false
    private val _menuListDataUiModel = MutableStateFlow(ArrayList<MenuListDataUiModel>())
    val menuListDataUiModel: StateFlow<ArrayList<MenuListDataUiModel>> = _menuListDataUiModel

    private val _profileCardState = MutableLiveData<ProfileCardState>()
    val profileCardState: LiveData<ProfileCardState> = _profileCardState

    private var profileCardData: MenuCardUiModel = MenuCardUiModel(
        MenuCardType.PROFILE_CARD,
        R.string.complete_profile_title,
        R.string.complete_profile_body,
        R.string.complete_profile_action,
        R.drawable.ic_user_profile
    )

    init {
        _menuListDataUiModel.value.apply {
            add(verifyMenuCardData())
            add(completeYourProfileMenuCardData())
            add(eventTicketMenuCardData())
            add(menuButtonsItem())
        }
    }

    private fun verifyMenuCardData(): MenuListDataUiModel {
        return MenuListDataUiModel(
            MenuListItemType.MENU_CARD_ITEM, MenuCardUiModel(
                MenuCardType.VERIFY_CARD,
                R.string.get_verified_title,
                R.string.get_verified_body,
                R.string.get_verified_action,
                R.drawable.get_verified_card_image
            )
        )
    }

    private fun completeYourProfileMenuCardData(): MenuListDataUiModel {
        return MenuListDataUiModel(MenuListItemType.MENU_CARD_ITEM, profileCardData)
    }

    private fun eventTicketMenuCardData(): MenuListDataUiModel {
        return MenuListDataUiModel(
            MenuListItemType.MENU_CARD_ITEM, MenuCardUiModel(
                MenuCardType.E_TICKET_CARD,
                R.string.event_ticket_title,
                R.string.event_ticket_body,
                cardImage = R.drawable.event_etickets_card_image
            )
        )
    }

    private fun menuButtonsItem(): MenuListDataUiModel {
        return MenuListDataUiModel(
            MenuListItemType.MENU_BUTTON_ITEM, EMPTY
        )
    }

    fun completeProfile() {
        viewModelScope.launch {
            updateCompleteYourProfileCard(ProfileCardState.Loading(true))
            delay(700L) // giving a minor delay to show the loading
            when (val result = profileUseCase.completeProfileUseCase()) {
                is NetworkResource.Success -> {
                    updateCompleteYourProfileCard(
                        ProfileCardState.Success(result.data)
                    )
                }
                is NetworkResource.Error -> {
                    updateCompleteYourProfileCard(ProfileCardState.Error(result.errorMessage))

                }
            }
        }
    }

    private fun updateCompleteYourProfileCard(state: ProfileCardState) {
        //1st update the data in the ui list
        profileCardData.profileCardUpdatedData = state
        //sending the updates to fragment
        _profileCardState.value = state
        val profileCardUpdate =
            MenuListDataUiModel(MenuListItemType.MENU_CARD_ITEM, profileCardData)
        // as listIndex[1] == profileCard is profile card so updating the list
        _menuListDataUiModel.value[PROFILE_CARD_INDEX] = profileCardUpdate
    }
}