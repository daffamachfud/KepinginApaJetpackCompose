package com.daffa.kepinginapajetpack.data.source.local

import com.daffa.kepinginapajetpack.data.source.local.entity.WishlistEntity
import com.daffa.kepinginapajetpack.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val mAppDao: AppDao){
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }

    fun insertWishlist(wish: WishlistEntity) = mAppDao.insertWish(wish)

    fun getAllWishlist() : Flow<List<WishlistEntity>> = mAppDao.getAllWishlist()

    fun deleteWish(wish:WishlistEntity) = mAppDao.deleteWish(wish.id)

    fun isAddedWishlist(id: Int): Boolean {
        return mAppDao.isAddedWishlist(id) != 0
    }
}