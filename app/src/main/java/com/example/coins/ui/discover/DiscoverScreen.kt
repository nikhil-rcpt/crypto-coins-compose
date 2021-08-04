package com.example.coins.ui.discover

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.example.coins.R
import com.example.coins.ui.discover.latestNews.LatestNewsSection


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun DiscoverScreen(name: String, vm: DiscoverViewModel) {

    val viewState by vm.liveData.observeAsState()



    viewState?.let { state ->

        LazyColumn(
            Modifier
                .background(MaterialTheme.colors.background)
//                .verticalScroll(rememberScrollState())
        ) {

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = name.capitalize(Locale.current),
                    style = MaterialTheme.typography.h5,
                    color = Color.Black, modifier = Modifier.padding(start = 16.dp)
                )


            }
            stickyHeader() {

                Column(modifier = Modifier.background(color = colorResource(R.color.white))) {

                    Spacer(modifier = Modifier.height(16.dp))


                    LazyRow(
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                        modifier = Modifier.clipToBounds()

                    ) {
                        items(items = state?.listingTabs ?: listOf(), itemContent = {
                            FiltersOptions(
                                text = intArrayOf(it.res),
                                selected = state?.selectedListingTab == it,
                                modifier = Modifier,
                                onSelection = { vm.onListingTabSelection(it) }
                            )
                        })
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
            when (state.selectedListingTab) {
                is DiscoverListingCategory.Latest -> item {
                    LatestNewsSection(name, vm, state)
                }
                is DiscoverListingCategory.Blog -> {
                }
                is DiscoverListingCategory.TopGainers -> {
                }
                is DiscoverListingCategory.TopLosers -> {
                }
            }
        }


    }


}


@Composable
fun cardButtonVariant() {

    Card(

        elevation = 2.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        backgroundColor = colorResource(R.color.light_button_gray_bg)


    ) {

        Row(
            modifier = Modifier.padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("lkjsdf")
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun FiltersOptions(
    vararg text: Int,
    selected: Boolean,
    modifier: Modifier,
    onSelection: () -> Unit,
) {

    val color = when {
        selected -> colorResource(R.color.black)
//            MaterialTheme.colors.primacry.copy(alpha = 0.08f)
        else -> colorResource(R.color.light_button_gray_bg)

//                .copy(alpha = 0.12f)
    }

    Surface(
        color = color,
        contentColor = when {
            selected -> colorResource(id = R.color.white)
//                MaterialTheme.colors.primary
            else -> MaterialTheme.colors.onSurface
        },
        //see CoinShape. which affect small medium large shape roundness via CoinTheme.
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
        onClick = { onSelection.invoke() }
    ) {
        Text(
            text = getDisplayResource(input = text),
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun getDisplayResource(vararg input: Int): String {
    return if (input.isEmpty()) ""
    else {
        var a = ""
        input.forEach { a += stringResource(it) + " " }
        a
    }
}


//@Preview
//@Composable
//fun PreviewFirstScreen() {
//    ListingScreen("Android", HomeViewModel())
//}
