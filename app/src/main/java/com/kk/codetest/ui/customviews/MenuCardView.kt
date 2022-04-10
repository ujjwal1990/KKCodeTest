package com.kk.codetest.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.kk.codetest.R
import com.kk.codetest.common.utils.Constants.Companion.EMPTY
import com.kk.codetest.databinding.ViewCustomMenuCardBinding
import com.kk.codetest.ui.utils.hide
import com.kk.codetest.ui.utils.setVisibility
import com.kk.codetest.ui.utils.show

//Custom cardview to manage the views inside it
class MenuCardView constructor(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {
    private var binding: ViewCustomMenuCardBinding = ViewCustomMenuCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )
    private var cardTitleAttr: String? = null
    private var cardBodyAttr: String? = null
    private var cardLeftImageAttr: Int? = null
    private var cardLeftBackgroundColorAttr: Int? = null
    private var cardRightImageAttr: Int? = null
    private var buttonSmallButtonTextAttr: String? = null
    private var buttonLargeButtonTextAttr: String? = null
    private var cardLeftImageVisibilityAttr: Boolean = false
    private var cardRightImageVisibilityAttr: Boolean = false
    private var cardSmallButtonVisibilityAttr: Boolean = false
    private var cardLargeButtonVisibilityAttr: Boolean = false
    private var buttonTypeAttr: Int = -1

    enum class CardButtonType() {
        WARNING_BUTTON, SUCCESS_BUTTON, ACTION_BUTTON
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MenuCardView)
        try {
            ta.apply {
                cardTitleAttr = getString(R.styleable.MenuCardView_cardTitle)
                cardBodyAttr = getString(R.styleable.MenuCardView_cardBody)
                cardLeftImageAttr = getResourceId(R.styleable.MenuCardView_cardLeftImage, -1)
                cardLeftBackgroundColorAttr =
                    getResourceId(R.styleable.MenuCardView_cardLeftBackgroundColor, -1)
                cardRightImageAttr = getResourceId(R.styleable.MenuCardView_cardRightImage, -1)
                buttonSmallButtonTextAttr =
                    getString(R.styleable.MenuCardView_buttonSmallButtonText)
                buttonLargeButtonTextAttr =
                    getString(R.styleable.MenuCardView_buttonLargeButtonText)
                cardLeftImageVisibilityAttr =
                    getBoolean(R.styleable.MenuCardView_cardLeftImageVisibility, false)
                cardRightImageVisibilityAttr =
                    getBoolean(R.styleable.MenuCardView_cardRightImageVisibility, false)
                cardSmallButtonVisibilityAttr =
                    getBoolean(R.styleable.MenuCardView_cardSmallButtonVisibility, false)
                cardLargeButtonVisibilityAttr =
                    getBoolean(R.styleable.MenuCardView_cardLargeButtonVisibility, false)
                buttonTypeAttr = getInteger(R.styleable.MenuCardView_buttonType, -1)

                setCardTitle(cardTitleAttr)
                setCardBody(cardBodyAttr)
                setCardLeftImageBackGroundColor(cardLeftBackgroundColorAttr)
                setCardLeftImage(cardLeftImageAttr)
                setCardRightImage(cardRightImageAttr)
                setSmallButtonText(buttonSmallButtonTextAttr)
                setLargeButtonText(buttonLargeButtonTextAttr)
                setCardLeftImageVisibility(cardLeftImageVisibilityAttr)
                setCardRightImageVisibility(cardRightImageVisibilityAttr)
                setCardSmallButtonVisibility(cardSmallButtonVisibilityAttr)
                setCardLargeButtonVisibility(cardLargeButtonVisibilityAttr)
                setLargeButtonType(buttonTypeAttr)
            }
        } finally {
            ta.recycle()
        }
    }

    fun setCardTitle(cardTitle: String?) {
        binding.cardTitle.text = cardTitle ?: EMPTY
    }

    fun setCardBody(cardBody: String?) {
        binding.cardBodyText.text = cardBody ?: EMPTY
    }

    fun setCardLeftImage(leftImage: Int?) {
        if (leftImage != null && leftImage != -1) {
            binding.cardLeftImage.setImageResource(leftImage)
            binding.cardLeftImage.show()
        } else binding.cardLeftImage.hide()
    }

    fun setCardLeftImageBackGroundColor(leftImageBg: Int?) {
        if (leftImageBg != null && leftImageBg != -1) {
            binding.cardLeftImage.background = ContextCompat.getDrawable(context, leftImageBg)
            binding.cardLeftImage.show()
        } else binding.cardLeftImage.hide()
    }

    fun setCardRightImage(rightImage: Int?) {
        if (rightImage != null && rightImage != -1) {
            binding.cardRightImage.setImageResource(rightImage)
            binding.cardRightImage.show()
        } else binding.cardRightImage.hide()
    }

    fun setSmallButtonText(smallButtonText: String?) {
        binding.cardSmallButton.text = smallButtonText ?: EMPTY
    }

    fun setLargeButtonText(largeButtonText: String?) {
        binding.cardLargeButton.text = largeButtonText ?: EMPTY
    }

    fun setCardLeftImageVisibility(leftImageVisibility: Boolean) {
        binding.cardLeftImage.setVisibility(leftImageVisibility)
    }

    fun setCardRightImageVisibility(rightImageVisibility: Boolean) {
        binding.cardRightImage.setVisibility(rightImageVisibility)
    }

    fun setCardSmallButtonVisibility(smallButtonVisibility: Boolean) {
        binding.cardSmallButton.setVisibility(smallButtonVisibility)
    }

    fun setCardLargeButtonVisibility(largeButtonVisibility: Boolean) {
        binding.cardLargeButton.setVisibility(largeButtonVisibility)
    }

    fun setLargeButtonType(buttonType: Int) {
        binding.cardLargeButton.background = when (buttonType) {
            CardButtonType.WARNING_BUTTON.ordinal -> ContextCompat.getDrawable(
                context,
                R.drawable.warning_button_background
            )
            CardButtonType.SUCCESS_BUTTON.ordinal -> ContextCompat.getDrawable(
                context,
                R.drawable.success_button_background
            )
            CardButtonType.ACTION_BUTTON.ordinal -> ContextCompat.getDrawable(
                context,
                R.drawable.action_button_background
            )
            else -> ContextCompat.getDrawable(context, R.drawable.action_button_background)
        }
    }


    fun setCardTitleTextAppearance(textAppearance: Int) {
        binding.cardTitle.setTextAppearance(textAppearance)
    }

    fun setCardTitleTextColor(color: Int) {
        binding.cardTitle.setTextColor(color)
    }

    fun setCardBodyTextColor(color: Int) {
        binding.cardBodyText.setTextColor(color)
    }

    fun setCardRightImageMargin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        val layoutParams = binding.cardRightImage.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.setMargins(left, top, right, bottom)
        binding.cardRightImage.layoutParams = layoutParams
    }

    fun setCardBackground(background: Int) {
        binding.menuCard.setBackgroundResource(background)
    }

    fun setCardBodyTextPadding(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        binding.cardBodyText.setPadding(left, top, right, bottom)
    }
}