package com.kk.codetest.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kk.codetest.databinding.ItemRowMenuButtonLayoutBinding
import com.kk.codetest.databinding.ItemRowMenuLayoutBinding
import com.kk.codetest.ui.menu.models.MenuListDataUiModel
import com.kk.codetest.ui.menu.models.MenuListItemType
import com.kk.codetest.ui.menu.viewholder.MenuButtonViewHolder
import com.kk.codetest.ui.menu.viewholder.MenuCardViewHolder
import com.kk.codetest.ui.utils.CommonViewHolder

class MenuCardsAdapter(
    private val menuListData: ArrayList<MenuListDataUiModel>,
    private val onItemClicked: (Int) -> Unit
) :
    RecyclerView.Adapter<CommonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return if (viewType == MenuListItemType.MENU_CARD_ITEM.ordinal) {
            MenuCardViewHolder(
                ItemRowMenuLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClicked
            )
        } else {
            MenuButtonViewHolder(
                ItemRowMenuButtonLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.bind(menuListData[position], position)
    }

    override fun getItemCount(): Int = menuListData.size

    override fun getItemViewType(position: Int): Int {
        return menuListData[position].menuListItemType.ordinal
    }
}