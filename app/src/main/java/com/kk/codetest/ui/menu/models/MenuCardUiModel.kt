package com.kk.codetest.ui.menu.models

import com.kk.codetest.ui.menu.profile.ProfileCardState

data class MenuCardUiModel(
    var cardType: MenuCardType,
    var cardTitleId: Int,
    var cardBodyId: Int,
    var cardButtonTextId: Int? = null,
    var cardImage: Int,
    var profileCardUpdatedData: ProfileCardState? = null
)