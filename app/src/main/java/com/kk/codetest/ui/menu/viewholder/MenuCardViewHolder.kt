package com.kk.codetest.ui.menu.viewholder

import androidx.core.content.ContextCompat
import com.kk.codetest.R
import com.kk.codetest.ui.customviews.MenuCardView
import com.kk.codetest.databinding.ItemRowMenuLayoutBinding
import com.kk.codetest.ui.menu.models.MenuCardType
import com.kk.codetest.ui.menu.models.MenuCardUiModel
import com.kk.codetest.ui.menu.models.MenuListDataUiModel
import com.kk.codetest.ui.utils.CommonViewHolder

class MenuCardViewHolder(private val viewBinding: ItemRowMenuLayoutBinding) :
    CommonViewHolder(viewBinding) {
    private lateinit var menuListDataUiModel: MenuListDataUiModel
    private lateinit var menuCardUiModel: MenuCardUiModel
    override fun bind(menuListDataUiModel: MenuListDataUiModel, position: Int) {
        super.bind(menuListDataUiModel, position)
        this.menuListDataUiModel = menuListDataUiModel
        menuCardUiModel = this.menuListDataUiModel.data as MenuCardUiModel
        setupCardView()
        when (menuCardUiModel.cardType) {
            MenuCardType.VERIFY_CARD -> {
                setupVerifyCard()
            }
            MenuCardType.PROFILE_CARD -> {
                setupProfileCard()
            }
            MenuCardType.E_TICKET_CARD -> {
                setupETicketCard()
            }
        }
    }

    private fun setupCardView() {
        with(viewBinding.root) {
            setCardTitle(context.getString(menuCardUiModel.cardTitleId))
            setCardBody(context.getString(menuCardUiModel.cardBodyId))
        }
    }

    private fun setupVerifyCard() {
        with(viewBinding.root) {
            setCardLeftImageVisibility(false)
            setCardLargeButtonVisibility(false)
            menuCardUiModel.cardButtonTextId?.let { setSmallButtonText(context.getString(it)) }
            setCardRightImage(menuCardUiModel.cardImage)
            setCardRightImageMargin(right = context.resources.getDimensionPixelSize(R.dimen.spacing_medium))
            setCardSmallButtonVisibility(true)
            setCardRightImageVisibility(true)
        }
    }

    private fun setupProfileCard() {
        with(viewBinding.root) {
            setCardSmallButtonVisibility(false)
            setCardRightImageVisibility(false)
            menuCardUiModel.cardButtonTextId?.let { setLargeButtonText(context.getString(it)) }
            setCardLeftImage(menuCardUiModel.cardImage)
            setCardLeftImageBackGroundColor(R.drawable.warning_ring)
            setCardTitleTextAppearance(R.style.TextAppearance_KK_Headline2)
            setLargeButtonType(MenuCardView.CardButtonType.ACTION_BUTTON.ordinal)
            setCardLeftImageVisibility(true)
            setCardLargeButtonVisibility(true)
        }
    }

    private fun setupETicketCard() {
        with(viewBinding.root) {
            setCardLeftImageVisibility(false)
            setCardLargeButtonVisibility(false)
            setCardSmallButtonVisibility(false)
            setCardRightImage(menuCardUiModel.cardImage)
            setCardBodyTextPadding(bottom = context.resources.getDimensionPixelSize(R.dimen.spacing_medium))
            setCardBodyTextColor(ContextCompat.getColor(context, R.color.white))
            setCardTitleTextColor(ContextCompat.getColor(context, R.color.white))
            setCardRightImageVisibility(true)
            setCardBackground(R.drawable.event_card_background)
        }
    }
}