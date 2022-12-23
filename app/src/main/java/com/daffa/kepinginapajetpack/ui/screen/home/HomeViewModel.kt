package com.daffa.kepinginapajetpack.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daffa.kepinginapajetpack.data.AppRepository
import com.daffa.kepinginapajetpack.model.Wishlist
import com.daffa.kepinginapajetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AppRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Wishlist>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Wishlist>>>
        get() = _uiState

    fun getAllSuggestionWish() {
        viewModelScope.launch {
            repository.getAllWishlist()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { wish ->
                    _uiState.value = UiState.Success(wish)
                }
        }
    }

//    private val _query = mutableStateOf("")
//    val query: State<String> get() = _query
//
//    fun searchWish(newQuery: String) {
//        _query.value = newQuery
//        _uiState.value = UiState.Success(repository.searchWish(_query.value).sortedBy { it.wish.name })
//    }
}