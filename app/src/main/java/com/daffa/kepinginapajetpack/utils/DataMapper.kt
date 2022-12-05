package com.daffa.kepinginapajetpack.utils

import com.daffa.kepinginapajetpack.data.source.local.entity.WishlistEntity
import com.daffa.kepinginapajetpack.model.Wish

object DataMapper {
    fun mapWishModelToEntity(wish: Wish): WishlistEntity {
        return WishlistEntity(
            wish.id,
            wish.name,
            wish.category,
            wish.description,
            wish.price,
            wish.link,
            wish.image,
            wish.deleted
        )
    }

    fun mapWishEntityToModel(wishlistEntity: WishlistEntity): Wish {
        return Wish(
            wishlistEntity.id,
            wishlistEntity.name,
            wishlistEntity.image,
            wishlistEntity.category,
            wishlistEntity.description,
            wishlistEntity.price,
            wishlistEntity.link,
            wishlistEntity.deleted
        )
    }
}