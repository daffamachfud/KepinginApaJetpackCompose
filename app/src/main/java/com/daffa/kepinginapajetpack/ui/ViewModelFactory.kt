package com.daffa.kepinginapajetpack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daffa.kepinginapajetpack.data.AppRepository
import com.daffa.kepinginapajetpack.ui.screen.detail.DetailWishViewModel
import com.daffa.kepinginapajetpack.ui.screen.home.HomeViewModel
import com.daffa.kepinginapajetpack.ui.screen.wishlist.WishlistViewModel

class ViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailWishViewModel::class.java)) {
            return DetailWishViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
            return WishlistViewModel(repository) as T
        }
//        else if (modelClass.isAssignableFrom(DetailRewardViewModel::class.java)) {
//            return DetailRewardViewModel(repository) as T

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}