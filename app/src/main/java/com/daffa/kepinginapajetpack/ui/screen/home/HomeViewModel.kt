package com.daffa.kepinginapajetpack.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daffa.kepinginapajetpack.data.AppRepository
import com.daffa.kepinginapajetpack.model.Wish
import com.daffa.kepinginapajetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AppRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Wish>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Wish>>>
        get() = _uiState

    fun getAllSuggestionWish() {
        viewModelScope.launch {
            repository.getSuggestionWishList()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { wish ->
                    _uiState.value = UiState.Success(wish)
                }
        }
    }
}