package com.kk.codetest.ui.menu.profile

import com.kk.codetest.domain.models.Profile

sealed class ProfileCardState {
    data class Loading(var isLoading: Boolean) : ProfileCardState()
    data class Success(var data: Profile?) : ProfileCardState()
    data class Error(var errorMessage: String?) : ProfileCardState()
}