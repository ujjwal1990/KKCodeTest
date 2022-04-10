package com.kk.codetest.ui.menu

import androidx.lifecycle.ViewModel
import com.kk.codetest.R
import com.kk.codetest.common.utils.Constants.Companion.EMPTY
import com.kk.codetest.ui.menu.models.MenuCardType
import com.kk.codetest.ui.menu.models.MenuCardUiModel
import com.kk.codetest.ui.menu.models.MenuListDataUiModel
import com.kk.codetest.ui.menu.models.MenuListItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {
    val menuListDataUiModel = MutableStateFlow(ArrayList<MenuListDataUiModel>())
    init {
        menuListDataUiModel.value.apply {
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
        return MenuListDataUiModel(
            MenuListItemType.MENU_CARD_ITEM, MenuCardUiModel(
                MenuCardType.PROFILE_CARD,
                R.string.complete_profile_title,
                R.string.complete_profile_body,
                R.string.complete_profile_action,
                R.drawable.ic_user_profile
            )
        )
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
}