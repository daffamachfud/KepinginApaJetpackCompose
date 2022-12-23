package com.daffa.kepinginapajetpack.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daffa.kepinginapajetpack.data.AppRepository
import com.daffa.kepinginapajetpack.model.Wish
import com.daffa.kepinginapajetpack.model.Wishlist
import com.daffa.kepinginapajetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailWishViewModel(
    private val repository: AppRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Wishlist>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Wishlist>>
        get() = _uiState

    fun getWishById(wishId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getWishlistById(wishId))
        }
    }

    fun addToWishlist(wish: Wish, value: Boolean) {
        viewModelScope.launch {
            repository.updateWishlist(wish.id, value)
        }
    }
}