package com.compose.mysoothee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.mysoothee.ui.theme.PlaygroundTheme2

// ----------- Data ------------

val bodyDrawables = intArrayOf(
    R.drawable.ab1_inversions,
    R.drawable.ab2_quick_yoga,
    R.drawable.ab3_stretching,
    R.drawable.ab4_tabata,
    R.drawable.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga,
)

val bodyTitleList = intArrayOf(
    R.string.ab1_inversions,
    R.string.ab2_yoga,
    R.string.ab3_stretching,
    R.string.ab4_tobata,
    R.string.ab5_hilt,
    R.string.ab6_natal_yoga,
)

// ----------- Data -----------

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme2 {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Content()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlaygroundTheme2 {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Content()
        }
    }
}

@Composable
fun Content() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar()
        Spacer(modifier = Modifier.size(height = 10.dp, width = 0.dp))
        AlignYourBodyRow()
        FavoriteCollectionsGrid()
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
    )
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
) {
    val alignYourBodyData = mutableListOf<AlignYourBodyData>()
    for (i in 0..5) {
        alignYourBodyData.add(
            AlignYourBodyData(
                drawable = bodyDrawables[i],
                text = bodyTitleList[i],
            ),
        )
    }
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,

            modifier = Modifier.size(size = 88.dp).clip(
                CircleShape,
            ),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = stringResource(id = text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            modifier = Modifier.width(255.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier,
) {
    val favoriteCollectionsData = mutableListOf<AlignYourBodyData>()
    for (i in 0..5) {
        favoriteCollectionsData.add(
            AlignYourBodyData(
                drawable = bodyDrawables[i],
                text = bodyTitleList[i],
            ),
        )
    }
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.heightIn(max = 160.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}
