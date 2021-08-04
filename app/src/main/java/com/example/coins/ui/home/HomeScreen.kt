package com.example.coins.ui.home

import android.graphics.*
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coins.R
import com.example.coins.data.latestListing.CoinDataItem
import com.example.coins.theme.CoinTheme
import com.example.coins.theme.changeStatusBarColor
import com.example.coins.theme.findWindow
import com.example.coins.util.getCoinImagePath
import com.example.coins.util.getFormattedCoinPrice
import com.example.coins.util.roundTo
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.absoluteValue


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.example.coins.ui.customUi.CoinGraph

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(name: String, vm: HomeScreenViewModel) {
    val systemController = rememberSystemUiController()
    systemController.changeStatusBarColor(Color.Black, false)
    val rectangle = Rect()
    val view =
        LocalView.current.context.findWindow()?.decorView?.getWindowVisibleDisplayFrame(rectangle)
    val statusBarHeight = rectangle.top
    val viewState by vm.liveData.observeAsState()
    val mainToolbarHeight = 134.dp
    val mainTickerBarHeight = 50.dp
    val offsetScrollSpeed =
        ((mainToolbarHeight.value + statusBarHeight - 8.dp.value) / 2) / mainTickerBarHeight.value

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {


//        Image(
//            painter = painterResource(id = R.drawable.coin_banner),
//            contentDescription = "",
//            modifier = Modifier.height(900.dp), contentScale = ContentScale.Crop
//        )

        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.black))
                .fillMaxWidth()
                .height(mainToolbarHeight)
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(mainTickerBarHeight)
                    .background(
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        ), color = colorResource(id = R.color.home_blue)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "BTC Dominance: 45.92%  -  ETH Dominance:17.63%  -  Cryptocurrencies 10,985  -  Market Pairs: 40,747",
                    style = MaterialTheme.typography.caption.copy(color = colorResource(id = R.color.white)),
                    maxLines = 1, modifier = Modifier.horizontalScroll(rememberScrollState())
                )

            }
        }

        val st = rememberLazyListState()

        LazyColumn(
            state = st
        ) {
//                            item {
//            HomeToolbar()
//        }
//          st = scrollstate
            item {
                Column(
                    modifier = Modifier
                        .height(mainToolbarHeight)
                        .fillMaxWidth()
//                       .background(colorResource(id = R.color.loss))
//
                ) {
                }
            }

//            stickyHeader {
//                Column(
//                    modifier = Modifier
//                        .height(110.dp)
//                        .fillMaxWidth()
//                            .background(colorResource(id = R.color.loss)
////
//                ) ){
//                }
//            }
//
//                    item{
//                        Spacer(modifier =Modifier.height(100.dp))
//                    }

            item {

//                        Spacer(modifier =Modifier.height(32.dp))
                Column(
                    modifier = Modifier
                        .background(
                            colorResource(id = R.color.white)
                        )
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(32.dp)
                    )

                    HomeQuickWidgetGrid(items = viewState?.actionsWidget ?: listOf(), column = 2)

                    viewState?.homeCoinList?.forEach {
                        Spacer(Modifier.height(32.dp))
                        HomeCoinsList(it.labelRes, true, it.getCoins())
                    }
                    Spacer(modifier = Modifier.height(120.dp))

                }

            }
        }
        HomeToolbar(st, mainToolbarHeight, offsetScrollSpeed, statusBarHeight)

    }
}


@ExperimentalAnimationApi
@Composable
fun HomeToolbar(
    scrollState: LazyListState,
    mainToolbarHeight: Dp,
    offsetScrollSpeed: Float,
    statusBarHeight: Int
) {

    val systemController = rememberSystemUiController()

    var elevation by remember { mutableStateOf(0.dp) }
    Card(
        backgroundColor = colorResource(id = R.color.black),
        elevation = elevation,
        shape = RoundedCornerShape(0.dp), modifier = Modifier.height(80.dp)
    ) {


        Log.i(
            "homescreen scrollstate ",
            "${scrollState.firstVisibleItemIndex}  ${scrollState.firstVisibleItemScrollOffset}   "
        )

        Box() {
//                Text(
//                    modifier = Modifier
//                        .padding(16.dp, 28.dp)
//,                    text = stringResource(id = R.string.home),
//                    style = MaterialTheme.typography.body2
//                )

//                var ty by remember { mutableStateOf(-140f) }
            val mainOffset = -(mainToolbarHeight.value + (statusBarHeight.toFloat()))
//                var mainOffset = -160f
            var ty by remember {
                mutableStateOf(-mainOffset)
            }

            val pos = scrollState.firstVisibleItemIndex == 0
            if (pos) {
                if (mainOffset + scrollState.firstVisibleItemScrollOffset.toFloat() * offsetScrollSpeed <= 0f) {
                    ty =
                        mainOffset + scrollState.firstVisibleItemScrollOffset.toFloat() * offsetScrollSpeed
                } else ty = 0f
            }

            elevation = if (ty == 0f) 2.dp else 0.dp

            if (ty == 0f) systemController.changeStatusBarColor(Color.White, true)
            else if (ty == mainOffset) systemController.changeStatusBarColor(Color.Black, false)

            Log.i("scroll transaltiony ", ty.toString())

            Column(
                modifier = Modifier
                    .graphicsLayer {
                        translationY = ty
                    }
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(colorResource(id = R.color.white))
            ) {
//                    Spacer(modifier = Modifier
//                        .graphicsLayer {
////                        alpha = min(1f, 1 - (scrollState.value / 600f))
//                            translationY = ty
//                        }
//                        .fillMaxWidth()
//                        .height(80.dp)
//                        .background(colorResource(id = R.color.white))
//                    )

                Text(
                    modifier = Modifier
                        .padding(16.dp, 28.dp), text = stringResource(id = R.string.home),
                    style = MaterialTheme.typography.body2
                )
            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 28.dp)
            ) {

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Icon(
                    imageVector = Icons.Filled.Search,
                    "Search Icon",
                    modifier = Modifier.padding(end = 16.dp),
                    tint = colorResource(id = R.color.icon_super_light_gray)
                )

                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    "Notification Icon", tint = colorResource(id = R.color.icon_super_light_gray)

                )

            }
        }


    }
}


@Composable
fun HomeCoinsList(@StringRes title: Int, viewMore: Boolean = true, items: List<CoinDataItem>) {
    BoxWithConstraints(
        Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
    ) {
        val width = (maxWidth / 2.6f)
        Column {
            HomeCoinListHeader(title, viewMore)
            LazyRow {
                items(items) {
                    HomeCoinListItem(it, width)
                }
            }
        }
    }
}

@Composable
fun HomeCoinListHeader(@StringRes title: Int, viewMore: Boolean) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = title),
            style = MaterialTheme.typography.h6
        )

        Text(
            text = stringResource(id = R.string.see_all),
            style = MaterialTheme.typography.body2
        )

    }
}

@Composable
fun HomeCoinListItem(item: CoinDataItem, width: Dp) {
    Card(
        elevation = 4.dp, shape = RoundedCornerShape(12.dp), modifier = Modifier
            .padding(8.dp)
            .width(width)
            .height(width)
    ) {
        Column(
            Modifier
                .padding(12.dp, 10.dp)
                .widthIn(),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Image(
                    painter = rememberCoilPainter(
                        item.id.toString().getCoinImagePath(),
                        fadeIn = true
                    ),
                    contentDescription = stringResource(R.string.coin_image_for, item.name ?: ""),
                    modifier = Modifier
                        .size(
                            32.dp
                        )
                        .clip(RoundedCornerShape(50))
                )

                    Box(
                        Modifier.fillMaxWidth()
                            .height(28.dp), contentAlignment = Alignment.Center
                    ) {
                        CoinGraph(styleThin = true)
                    }
            }

            Column {
                Text(
                    text = item.name ?: "",
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = getFormattedCoinPrice(coinPrice = item.quote?.inr?.price),
                    style = MaterialTheme.typography.body2
                )
            }

            val isPositive = item.quote?.inr?.percentChange24h ?: 0.0 > 0

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_up_arrow),
                    "Icon",
                    modifier = Modifier
                        .height(9.dp),
                    tint = if (isPositive) colorResource(R.color.gain) else colorResource(R.color.loss)
                )

                Text(
                    text = getFormattedCoinPrice(
                        coinPrice = Math.abs(
                            item.quote?.inr?.percentChange24h ?: 0.0
                        ).roundTo(2)
                    ),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun HomeQuickWidgetGrid(items: List<HomeActionWidget>, column: Int) {

    items.chunked(2).forEach {
        BoxWithConstraints(
            Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp)
        ) {
            val width = maxWidth / 2
            LazyRow {

                items(it) {
                    HomeQuickWidgetItem(it.labelRes, it.imgRes, Modifier.width(width))
                }
            }
        }

    }

}


@Composable
fun HomeQuickWidgetItem(name: Int, ic: Int, modifier: Modifier = Modifier) {

    Card(
        elevation = 4.dp, modifier = modifier
            .padding(6.dp, 6.dp), shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            Modifier.padding(12.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painterResource(ic),
                "Icon",
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(50))
            )
            Text(
                text = stringResource(name),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview("Coin Listing Item")
@Composable
fun HomeQuickWidgetItemPreview() {
    CoinTheme(false) {
        HomeQuickWidgetItem(R.string.watchlist, R.drawable.ic_selected)
    }
}

@Preview("Coin Listing Item - Dark")
@Composable
fun HomeQuickWidgetItemPreviewDark() {
    CoinTheme(true) {
        HomeQuickWidgetItem(R.string.watchlist, R.drawable.ic_selected)
    }
}

