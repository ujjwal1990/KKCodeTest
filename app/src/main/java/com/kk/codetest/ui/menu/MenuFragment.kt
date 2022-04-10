package com.kk.codetest.ui.menu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.kk.codetest.R
import com.kk.codetest.databinding.FragmentMenuBinding
import com.kk.codetest.ui.menu.adapter.MenuCardsAdapter
import com.kk.codetest.ui.menu.models.MenuListDataUiModel
import com.kk.codetest.ui.utils.hide
import com.kk.codetest.ui.utils.setVisibility
import com.kk.codetest.ui.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var _binding: FragmentMenuBinding? = null
    private var menuBarVisibility: Boolean = false
    private val binding get() = _binding!!
    private val bottomSheetBehavior: BottomSheetBehavior<View>
            by lazy { BottomSheetBehavior.from(binding.menuContentLayout.menuBottomSheet) }
    private val menuViewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)
        lifecycleScope.launch {
            menuViewModel.menuListDataUiModel.collect { menuItemsDataModel ->
                setUpMenuCardsData(menuItemsDataModel)
            }
        }
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.menuToolBarLayout.menuToolBar)
        showHideMenu()
        toolBarCloseClick()
    }

    private fun setUpMenuCardsData(menuItemsDataModel: ArrayList<MenuListDataUiModel>) {
        bottomSheetBehavior.isDraggable = false
        binding.menuContentLayout.menuCardsList.layoutManager = LinearLayoutManager(context)
        binding.menuContentLayout.menuCardsList.adapter = MenuCardsAdapter(menuItemsDataModel)
    }

    private fun toolBarCloseClick() {
        binding.menuToolBarLayout.toolBarClose.setOnClickListener {
            menuBarVisibility = false
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun showHideMenu() {
        binding.menuToolBarLayout.menuToolBar.setVisibility(menuBarVisibility)
        binding.menuContentLayout.menuButton.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                menuBarVisibility = false
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                menuBarVisibility = true
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        menuBarStateListener()
    }

    private fun menuBarStateListener() {
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.menuContentLayout.menuLayout.show()
                    binding.menuToolBarLayout.menuToolBar.setVisibility(menuBarVisibility)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.menuContentLayout.menuLayout.hide()
                binding.menuToolBarLayout.menuToolBar.setVisibility(menuBarVisibility)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}