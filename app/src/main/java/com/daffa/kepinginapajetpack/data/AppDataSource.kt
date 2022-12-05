package com.daffa.kepinginapajetpack.data

import com.daffa.kepinginapajetpack.data.source.local.entity.WishlistEntity
import com.daffa.kepinginapajetpack.model.Wish
import kotlinx.coroutines.flow.Flow

interface AppDataSource {
    fun getSuggestionWishList(): Flow<List<Wish>>
    fun getWishById(id:Int): Wish
    fun getAllWishlist(): Flow<List<WishlistEntity>>
    suspend fun insertWishList(wish: WishlistEntity)
    suspend fun deleteWish(wish: WishlistEntity)
    suspend fun isAddedWishlist(id: Int): Boolean
}