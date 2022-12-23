package com.daffa.kepinginapajetpack.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.daffa.kepinginapajetpack.R
import com.daffa.kepinginapajetpack.di.Injection
import com.daffa.kepinginapajetpack.ui.ViewModelFactory
import com.daffa.kepinginapajetpack.ui.common.UiState
import com.daffa.kepinginapajetpack.ui.component.WishlistButton
import com.daffa.kepinginapajetpack.ui.theme.KepinginApaJetpackTheme
import com.daffa.kepinginapajetpack.utils.formatCurrencyRupiah


@Composable
fun DetailScreen(
    wishId: Int,
    viewModel: DetailWishViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToWishlist: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getWishById(wishId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.wish.image ?: "",
                    data.wish.name ?: "",
                    data.wish.price ?: 0.0,
                    data.wish.description ?: "",
                    data.isAdded,
                    onBackClick = navigateBack,
                    onAddToWish = { value ->
                        viewModel.addToWishlist(data.wish, value)
                        navigateToWishlist()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    image: String,
    name: String,
    price: Double,
    description: String,
    isAdd: Boolean,
    onBackClick: () -> Unit,
    onAddToWish: (isAdd: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isAdded by rememberSaveable { mutableStateOf(isAdd) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = price.formatCurrencyRupiah(),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(LightGray)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            WishlistButton(
                isAdded = isAdded,
                onClick = {
                    onAddToWish(!isAdded)
                }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    KepinginApaJetpackTheme {
        DetailContent(
            "",
            "Xiaomi 200",
            7500.0,
            "test",
            false,
            onBackClick = {},
            onAddToWish = {}
        )
    }
}