package com.daffa.kepinginapajetpack.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.daffa.kepinginapajetpack.model.Wish
import com.daffa.kepinginapajetpack.model.Wishlist
import com.daffa.kepinginapajetpack.ui.theme.KepinginApaJetpackTheme
import com.daffa.kepinginapajetpack.utils.formatCurrencyRupiah
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeWishlist = Wishlist(
        wish = Wish(10, "Iphone", "", "", "", 180000.0, ""),
        isAdded = false,
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            KepinginApaJetpackTheme {
                DetailContent(
                    fakeWishlist.wish.image ?: "",
                    fakeWishlist.wish.name ?: "",
                    fakeWishlist.wish.price ?: 0.0,
                    fakeWishlist.wish.description ?: "",
                    fakeWishlist.isAdded,
                    onBackClick = {},
                    onAddToWish = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeWishlist.wish.name ?: "").assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeWishlist.wish.price.formatCurrencyRupiah())
            .assertIsDisplayed()
    }

}