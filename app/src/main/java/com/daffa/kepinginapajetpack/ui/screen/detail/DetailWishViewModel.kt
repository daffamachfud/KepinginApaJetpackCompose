package com.daffa.kepinginapajetpack.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daffa.kepinginapajetpack.data.AppRepository
import com.daffa.kepinginapajetpack.model.Wish
import com.daffa.kepinginapajetpack.ui.common.UiState
import com.daffa.kepinginapajetpack.utils.DataMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailWishViewModel(
    private val repository: AppRepository
) : ViewModel() {
    
    private val _uiState: MutableStateFlow<UiState<Wish>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Wish>>
        get() = _uiState

    fun getWishById(wishId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getWishById(wishId))
        }
    }

    fun isAddedWishlist(wishId: Int) : Boolean{
        viewModelScope.launch {
            returnedVal.value = repo.findbyID(id)

        }
    }

    fun addToWishlist(wish: Wish, value: Boolean) {
        viewModelScope.launch {
            if(value){
                repository.deleteWish(DataMapper.mapWishModelToEntity(wish))
            }else{
                repository.insertWishList(DataMapper.mapWishModelToEntity(wish))
            }
        }
    }
}