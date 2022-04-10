package com.kk.codetest.ui.menu.models

data class MenuCardUiModel(
    val cardType: MenuCardType,
    val cardTitleId: Int,
    val cardBodyId: Int,
    val cardButtonTextId: Int? = null,
    val cardImage: Int
)