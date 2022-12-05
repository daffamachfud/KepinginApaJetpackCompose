package com.daffa.kepinginapajetpack.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daffa.kepinginapajetpack.data.source.local.entity.WishlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWish(wish: WishlistEntity)

    @Query("Select * from wishlist where deleted = 0")
    fun getAllWishlist(): Flow<List<WishlistEntity>>

    @Query("UPDATE `wishlist` SET deleted=1 WHERE id = :id")
    fun deleteWish(id: Int?)

    @Query("Select Exists (Select * from wishlist where id = :id)")
    fun isAddedWishlist(id:Int): Int
}