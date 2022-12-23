package com.daffa.kepinginapajetpack.data

import com.daffa.kepinginapajetpack.model.FakeWishData
import com.daffa.kepinginapajetpack.model.Wishlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class AppRepository {
    private val wishlist = mutableListOf<Wishlist>()

    init {
        if (wishlist.isEmpty()) {
            FakeWishData.wishlist.forEach {
                wishlist.add(Wishlist(it, false))
            }
        }
    }

    fun getAllWishlist(): Flow<List<Wishlist>> = flowOf(wishlist)

    fun getWishlistById(wishId: Int): Wishlist = wishlist.first { it.wish.id == wishId }

    fun updateWishlist(wishId: Int?, isAdded: Boolean): Flow<Boolean> {
        val index = wishlist.indexOfFirst { it.wish.id == wishId }
        val result = if (index >= 0) {
            val wish = wishlist[index]
            wishlist[index] =
                wish.copy(wish = wish.wish, isAdded = isAdded)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedWish(): Flow<List<Wishlist>> {
        return getAllWishlist().map { wish ->
            wish.filter {
                it.isAdded
            }
        }
    }

//    fun searchWish(query: String): List<Wishlist>{
//        return wishlist.filter { it.wish.name!!.contains(query,ignoreCase = false) }
//    }

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(): AppRepository =
            instance ?: synchronized(this) {
                AppRepository().apply {
                    instance = this
                }
            }
    }

}