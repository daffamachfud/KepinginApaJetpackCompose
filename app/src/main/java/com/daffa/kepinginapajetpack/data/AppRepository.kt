package com.daffa.kepinginapajetpack.data

import com.daffa.kepinginapajetpack.data.source.local.LocalDataSource
import com.daffa.kepinginapajetpack.data.source.local.entity.WishlistEntity
import com.daffa.kepinginapajetpack.model.FakeWishData
import com.daffa.kepinginapajetpack.model.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppRepository private constructor(
    private val localDataSource: LocalDataSource
) : AppDataSource {
    private val wish = mutableListOf<Wish>()

    init {
        if (wish.isEmpty()) {
            FakeWishData.wishlist.forEach {
                wish.add(
                    Wish(
                        it.id,
                        it.name,
                        it.image,
                        it.category,
                        it.description,
                        it.price,
                        it.link,
                        it.deleted
                    )
                )
            }
        }
    }

    override suspend fun insertWishList(wish: WishlistEntity) = withContext(Dispatchers.IO) {
        localDataSource.insertWishlist(wish)
    }

    override suspend fun deleteWish(wish: WishlistEntity) = withContext(Dispatchers.IO){
        localDataSource.deleteWish(wish)
    }

    override suspend fun isAddedWishlist(id: Int): Boolean = withContext(Dispatchers.IO){
        localDataSource.isAddedWishlist(id)
    }

    override fun getSuggestionWishList(): Flow<List<Wish>> {
        return flowOf(wish)
    }

    override fun getWishById(id: Int): Wish {
        return wish.first {
            it.id == id
        }
    }


    override fun getAllWishlist(): Flow<List<WishlistEntity>> = localDataSource.getAllWishlist()

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(
            localDataSource: LocalDataSource
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(localDataSource)
            }
    }

}