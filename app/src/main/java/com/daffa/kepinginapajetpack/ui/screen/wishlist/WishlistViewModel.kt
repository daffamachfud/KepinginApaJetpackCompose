package com.daffa.kepinginapajetpack.ui.screen.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daffa.kepinginapajetpack.data.AppRepository
import com.daffa.kepinginapajetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val repository: AppRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<WishlistState>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<WishlistState>>
        get() = _uiState

    fun getAddedWishlist() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedWish()
                .collect { wish ->
                    _uiState.value = UiState.Success(WishlistState(wish))
                }
        }
    }

}