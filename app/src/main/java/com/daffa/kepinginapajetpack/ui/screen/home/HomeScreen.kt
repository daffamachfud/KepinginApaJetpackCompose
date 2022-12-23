package com.daffa.kepinginapajetpack.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daffa.kepinginapajetpack.R
import com.daffa.kepinginapajetpack.di.Injection
import com.daffa.kepinginapajetpack.model.Wishlist
import com.daffa.kepinginapajetpack.ui.ViewModelFactory
import com.daffa.kepinginapajetpack.ui.common.UiState
import com.daffa.kepinginapajetpack.ui.component.WishItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ), navigateToDetail: (Int?) -> Unit
) {
//    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllSuggestionWish()
            }
            is UiState.Success -> {
                HomeContent(
                    wish = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
//                    query = query,
//                    viewModel
                )
            }
            is UiState.Error -> {}
        }
    }

}

@Composable
fun HomeContent(
    wish: List<Wishlist>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int?) -> Unit,
//    query: String,
//    viewModel: HomeViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = modifier
                .height(35.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Gray)
                .padding(8.dp)
        ) {

            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.caption.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
//        SearchBar(query = query, onQueryChange = viewModel::searchWish)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(wish) { data ->
                WishItem(data.wish.image ?: "",
                    data.wish.name ?: "",
                    data.wish.price ?: 0.0,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.wish.id)
                    })
            }
        }
    }
}