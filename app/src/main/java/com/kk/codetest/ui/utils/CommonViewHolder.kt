package com.kk.codetest.ui.utils

import android.content.Context
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.kk.codetest.ui.menu.models.MenuListDataUiModel

open class CommonViewHolder constructor(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var _context: Context = binding.root.context
    val context = _context

    @CallSuper
    open fun bind(menuListDataUiModel: MenuListDataUiModel, position: Int) {
    }
}