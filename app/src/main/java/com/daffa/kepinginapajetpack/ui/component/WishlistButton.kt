package com.daffa.kepinginapajetpack.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daffa.kepinginapajetpack.R
import com.daffa.kepinginapajetpack.ui.theme.KepinginApaJetpackTheme

@Composable
fun WishlistButton(
    isAdded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = if(isAdded) Icons.Default.Close else Icons.Default.Check,
                    modifier = Modifier
                        .size(18.dp),
                    contentDescription = "drawable_icons"
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(if (isAdded) R.string.remove_from_wish else R.string.add_to_wish),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OrderButtonPreview() {
    KepinginApaJetpackTheme() {
        WishlistButton(
            isAdded = false,
            onClick = {}
        )
    }
}