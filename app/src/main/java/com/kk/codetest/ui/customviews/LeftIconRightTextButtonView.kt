package com.kk.codetest.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.kk.codetest.R
import com.kk.codetest.common.utils.Constants
import com.kk.codetest.databinding.ViewLefticonRightTextButtonBinding
import com.kk.codetest.ui.utils.hide
import com.kk.codetest.ui.utils.show

class LeftIconRightTextButtonView(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {
    private var leftIconAttr: Int? = null
    private var rightTextAttr: String? = null
    private var binding: ViewLefticonRightTextButtonBinding =
        ViewLefticonRightTextButtonBinding.inflate(
            LayoutInflater.from(context), this, true
        )

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.LeftIconRightTextButtonView)
        try {
            ta.apply {
                leftIconAttr = getResourceId(R.styleable.LeftIconRightTextButtonView_leftIcon, -1)
                rightTextAttr = getString(R.styleable.LeftIconRightTextButtonView_rightText)
                setLeftIcon(leftIconAttr)
                setRightText(rightTextAttr)
            }
        } finally {
            ta.recycle()
        }
    }

    fun setLeftIcon(leftIcon: Int?) {
        if (leftIcon != null && leftIcon != -1) {
            binding.leftIcon.setImageResource(leftIcon)
            binding.leftIcon.show()
        } else binding.leftIcon.hide()
    }

    fun setRightText(rightText: String?) {
        binding.rightText.text = rightText ?: Constants.EMPTY
    }
}