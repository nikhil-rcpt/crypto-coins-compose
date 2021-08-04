package com.example.coins.ui.listing

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
//import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coins.theme.ListingDividerGrey
import com.example.coins.util.CurrencyType
import com.example.coins.util.getFormattedCoinPrice


@Composable
fun ListingScreen(name: String, vm: ListingViewModel) {

    val viewState by vm.liveData.observeAsState()

    viewState?.let { state ->

        Column(Modifier.background(MaterialTheme.colors.background)) {


            Row(verticalAlignment = Alignment.CenterVertically){


                Surface(
                    Modifier
                        .height(TabHeight).weight(1f).padding(start=16.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(14.dp, Alignment.Start)) {
                        state.listingTabs.forEach { tab ->
                            CoinListingTab(
                                tab = tab,
//                                icon = screen.icon, //not using icon
                                onSelected = vm::onListingTabSelection,
                                selected = state.selectedListingTab == tab,
                            )
                        }
                    }
                }



//                ListingCategoriesTabs(
//                    categories = state.listingTabs,
//                    selectedCategory = state.selectedListingTab,
//                    onCategorySelected = vm::onListingTabSelection,
//                    modifier = Modifier.weight(1f)
//                )

                Icon(imageVector = Icons.Filled.Search,"Search Icon",modifier = Modifier.padding(end = 16.dp))
            }


            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start)
            ) {
                items(items = viewState?.listingFilters ?: listOf(), itemContent = {
                    FiltersOptions(
                        text = it.getDisplayName(),
                        selected = viewState?.filterSelected == it.id,
                        modifier = Modifier
                    )
                })
            }



            Spacer(Modifier.height(4.dp))


            LazyColumn {
                items(items = viewState?.latestCryptoListing?.data?.filterNotNull() ?: listOf(),
                    itemContent = {
                        CoinListingItem(
                            CoinListingItemData(
                                it.id.toString(),
                                it.name ?: "", it.symbol ?: "", (it.cmcRank ?: 0).toString(),
                                getFormattedCoinPrice(CurrencyType.INR, it.quote?.inr?.price),
                                getFormattedCoinPrice(CurrencyType.INR, it.quote?.inr?.marketCap)
                                //todo get currency from prefs //maybe util fun/class should have current curr
                            )
                        )

                        Divider(color = ListingDividerGrey, thickness = 0.5.dp)
                    })
            }

        }
    }
}




@Composable
private fun CoinListingTab(
    tab: CoinListingCategory,
//    icon: ImageVector,
    onSelected: (CoinListingCategory) -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colors.onSurface
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )

//    selected: Boolean,
//    interactionState: InteractionState,
//    indication: Indication?,
//    enabled: Boolean = true,
//    role: Role? = null,
//    onClick: () -> Unit
    Row(
        modifier = Modifier
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected, remember { MutableInteractionSource () }, rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            ) { onSelected.invoke(tab) },verticalAlignment = Alignment.CenterVertically) {
//        Icon(imageVector = icon, contentDescription = null, tint = tabTintColor)
//        if (selected) {
//            Spacer(Modifier.preferredWidth(12.dp))
            Text(stringResource(tab.res), color = tabTintColor,fontSize= if(selected) 18.sp else 16.sp)
//        }
    }
}


@Composable
private fun ListingCategoriesTabs(
    categories: List<CoinListingCategory>,
    selectedCategory: CoinListingCategory,
    onCategorySelected: (CoinListingCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = categories.indexOfFirst { it == selectedCategory }

    //    val indicator = @Composable { tabPositions: List<TabPosition> ->
    //        ListingCategoryIndicator(
    //            Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
    //        )
    //    }

    val indicator = @Composable { _ : List<TabPosition> ->  } //empty indicator

    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = indicator,
        modifier = modifier,
        divider =  @Composable{ },//empty divider below tab layout
        backgroundColor = MaterialTheme.colors.background,
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                modifier = Modifier.padding(start=0.dp,end=0.dp),
                selected = index == selectedIndex,
                onClick = { onCategorySelected(category) },
                text = {
                    Text(
                        text = stringResource(category.res),
                        style = MaterialTheme.typography.body2
                    )
                }
            )
        }
    }
}

@Composable
fun ListingCategoryIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface
) {
    Spacer(
        modifier
            .padding(horizontal = 24.dp)
            .height(4.dp)
//            .background(color,  RoundedCornerShape(topRightPercent = 100, topLeftPercent = 100))
    )
}


@Composable
private fun FiltersOptions(
    vararg text: Int,
    selected: Boolean,
    modifier: Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary.copy(alpha = 0.08f)
            else -> MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
        },
        contentColor = when {
            selected -> MaterialTheme.colors.primary
            else -> MaterialTheme.colors.onSurface
        },
        //see CoinShape. which affect small medium large shape roundness via CoinTheme.
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = getDisplayResource(input = text),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
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

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100