package com.kk.codetest.ui.menu.viewholder

import androidx.core.content.ContextCompat
import com.kk.codetest.R
import com.kk.codetest.ui.customviews.MenuCardView
import com.kk.codetest.databinding.ItemRowMenuLayoutBinding
import com.kk.codetest.ui.menu.models.MenuCardType
import com.kk.codetest.ui.menu.models.MenuCardUiModel
import com.kk.codetest.ui.menu.models.MenuListDataUiModel
import com.kk.codetest.ui.menu.profile.ProfileCardState
import com.kk.codetest.ui.utils.CommonViewHolder

class MenuCardViewHolder(
    private val viewBinding: ItemRowMenuLayoutBinding,
    private val onItemClicked: (Int) -> Unit
) :
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
            setCardTitleTextAppearance(R.style.TextAppearance_KK_Headline2)
            setCardLeftImage(menuCardUiModel.cardImage)
            setCardLeftImageVisibility(true)
            setCardLargeButtonVisibility(true)
            getLargeButtonView().setOnClickListener {
                onItemClicked(adapterPosition)
            }
            menuCardUiModel.profileCardUpdatedData?.let {
                when (it) {
                    is ProfileCardState.Loading -> {
                        showProgressBar(true)
                    }
                    is ProfileCardState.Success -> {
                        getLargeButtonView().setOnClickListener(null)
                        showProgressBar(false)
                        setCardTitle(it.data?.data?.title)
                        setCardTitleTextPadding(top = context.resources.getDimensionPixelSize(R.dimen.spacing_large))
                        setCardBody(it.data?.data?.message)
                        setLargeButtonText(context.getString(R.string.complete_profile_success))
                        setLargeButtonType(MenuCardView.CardButtonType.SUCCESS_BUTTON.ordinal)
                        setCardLeftImageBackGroundColor(R.drawable.success_ring)
                    }
                    is ProfileCardState.Error -> {
                        showProgressBar(false)
                        setLargeButtonText(context.getString(R.string.try_again))
                    }
                }
            } ?: {
                setCardLeftImageBackGroundColor(R.drawable.warning_ring)
                setLargeButtonType(MenuCardView.CardButtonType.ACTION_BUTTON.ordinal)
            }
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