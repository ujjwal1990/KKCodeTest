package com.kk.codetest.ui.menu.viewholder

import com.kk.codetest.databinding.ItemRowMenuButtonLayoutBinding
import com.kk.codetest.ui.menu.models.MenuListDataUiModel
import com.kk.codetest.ui.utils.CommonViewHolder

class MenuButtonViewHolder(private val viewBinding: ItemRowMenuButtonLayoutBinding) :
    CommonViewHolder(viewBinding) {
    private lateinit var menuListDataUiModel: MenuListDataUiModel
    override fun bind(menuListDataUiModel: MenuListDataUiModel, position: Int) {
        super.bind(menuListDataUiModel, position)
        this.menuListDataUiModel = menuListDataUiModel
    }
}