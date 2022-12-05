package com.daffa.kepinginapajetpack.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Wishlist : Screen("wishlist")
    object About : Screen("about")
    object DetailWish : Screen("home/{wishId}") {
        fun createRoute(wishId: Int?) = "home/$wishId"
    }
}