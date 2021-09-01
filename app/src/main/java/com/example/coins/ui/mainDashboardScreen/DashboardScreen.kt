package com.example.coins.ui.mainDashboardScreen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.coins.R
import com.example.coins.theme.ListingDividerGrey
import com.example.coins.ui.customUi.MainDashboardCoinGraph
import com.example.coins.ui.discover.getDisplayResource
import com.example.coins.ui.listing.CoinListingItemData
import com.example.coins.ui.listing.RankAndSymbol
import com.example.coins.util.CurrencyType
import com.example.coins.util.getCoinImagePath
import com.example.coins.util.getFormattedCoinPrice
import com.google.accompanist.coil.rememberCoilPainter

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun DashboardScreen(name: String, vm: DashboardViewModel) {

    val viewState by vm.liveData.observeAsState()


    viewState?.let { state ->

        LazyColumn(
            Modifier
                .background(MaterialTheme.colors.background)
//                .verticalScroll(rememberScrollState())
        ) {

            stickyHeader() {
                Card(
                    backgroundColor = colorResource(id = R.color.white),
                    elevation = 1.dp,
                    shape = RoundedCornerShape(0.dp), modifier = Modifier.height(80.dp)
                ) {


                    Box() {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(colorResource(id = R.color.white))
                        ) {//add header text
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
                                "Notification Icon",
                                tint = colorResource(id = R.color.icon_super_light_gray)

                            )

                        }
                    }
                }

            }

            item {

                Text(
                    modifier = Modifier.padding(16.dp, 8.dp),
                    text = stringResource(id = R.string.current_balance),
                    style = MaterialTheme.typography.body2
                )

                //current balance block

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = "\u20b9274,705",
                            style = MaterialTheme.typography.h5.copy(fontSize = 26.sp)
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = "+\u20b94,122 (24h)",
                            style = MaterialTheme.typography.subtitle2
                        )
                    }



                    Surface(
                        color = colorResource(id = R.color.gain_ticker_bg),
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text(
                            modifier = Modifier.padding(12.dp, 6.dp),
                            text = "1.52%",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Bold, color = colorResource(
                                    id = R.color.black
                                )
                            )
                        )
                    }
                }

                Spacer(Modifier.height(26.dp))

                Column(modifier = Modifier.background(color = colorResource(R.color.white))) {

                    LazyRow(
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(items = state.graphOptions ?: listOf(), itemContent = {
                            GraphTypeOptions(
                                text = intArrayOf(it.res),
                                selected = state.selectedGraphOption == it,
                                modifier = Modifier,
                                onSelection = { vm.onGraphOptionSelection(it) }
                            )
                        })
                    }

                }

                Spacer(Modifier.height(40.dp))


                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .padding(
                            bottom = 2.dp, start = 8.dp, end =
                            8.dp
                        ), contentAlignment = Alignment.Center
                ) {

                    MainDashboardCoinGraph(
                        styleThin = true,
                        dataSet = state.graphDataPoints
                    )
                }

                //graph end
                Spacer(Modifier.height(48.dp))

                Column(
                    modifier = Modifier
                        .background(color = colorResource(R.color.white))
                        .fillMaxWidth()
                ) {

                    LazyRow(
                        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                            Alignment.CenterHorizontally
                        )

                    ) {
                        items(items = state.graphRangeOptions ?: listOf(), itemContent = {
                            GraphRangeOptions(
                                text = intArrayOf(it.res),
                                selected = state.selectedGraphRangeOption == it,
                                modifier = Modifier,
                                onSelection = { vm.onGraphRangeOptionSelection(it) }
                            )
                        })
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.your_asset),
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )

                    HoldingsView(state.assetView.id, Modifier, vm)


                }
            }



            items(items = viewState?.latestCryptoListing?.data?.filterNotNull() ?: listOf(),
                itemContent = {
                    AssetListingItem(
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


@Composable
fun AssetListingItem(data: CoinListingItemData) {

    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {

        val typography = MaterialTheme.typography
        val (startBlock, endBlock) = createRefs()


        Row(
            Modifier
                .constrainAs(startBlock) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }, horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
        ) {

            Image(
                rememberCoilPainter(data.img.getCoinImagePath(), fadeIn = true),
                contentDescription = stringResource(R.string.coin_image_for, data.name),
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(50))
            )

            RankAndSymbol(name = data.name, rank = data.rank, symbol = data.symbol)

        }


        Column(Modifier.constrainAs(endBlock) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }, horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(8.dp)) {

            Text(text = data.price, style = MaterialTheme.typography.subtitle1)
            Text(
                text = "${stringResource(R.string.mcap)} ${data.mCap}",
                style = MaterialTheme.typography.caption, textAlign = TextAlign.End
            )
        }

    }
}


@ExperimentalMaterialApi
@Composable
fun HoldingsView(
    selected: String, modifier: Modifier, vm: DashboardViewModel

) {


    Surface(
        color = colorResource(R.color.light_button_gray_bg),
        //see CoinShape. which affect small medium large shape roundness via CoinTheme.
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
    ) {

        Row(modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)) {
            Surface(
                color = if (selected == AssetView.Value.id) colorResource(R.color.white)
                else colorResource(R.color.light_button_gray_bg),

                //see CoinShape. which affect small medium large shape roundness via CoinTheme.
                shape = MaterialTheme.shapes.small,
                modifier = modifier,
                onClick = { vm.setSelectedAssetView(AssetView.Value) }
            ) {
                Text(
                    text = "$",
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }

            Surface(
                color = if (selected == AssetView.Percentage.id) colorResource(R.color.white)
                else colorResource(R.color.light_button_gray_bg),

                //see CoinShape. which affect small medium large shape roundness via CoinTheme.
                shape = MaterialTheme.shapes.small,
                modifier = modifier,
                onClick = { vm.setSelectedAssetView(AssetView.Percentage) }
            ) {
                Text(
                    text = "%",
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }

    }
}


@ExperimentalMaterialApi
@Composable
fun GraphTypeOptions(
    vararg text: Int,
    selected: Boolean,
    modifier: Modifier,
    onSelection: () -> Unit,
) {

    Surface(
        color = colorResource(R.color.light_button_gray_bg),
        //see CoinShape. which affect small medium large shape roundness via CoinTheme.
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        onClick = { onSelection.invoke() }
    ) {
        Text(
            text = getDisplayResource(input = text),
            style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Medium,
                color = colorResource(
                    id =
                    if (selected
                    ) R.color.black else R.color.text_light_gray
                )
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}


@ExperimentalMaterialApi
@Composable
fun GraphRangeOptions(
    vararg text: Int,
    selected: Boolean,
    modifier: Modifier,
    onSelection: () -> Unit,
) {

    val color = when {
        selected -> colorResource(R.color.light_button_gray_bg)
        else -> Color.Transparent
    }


    Surface(
        color = color,
        //see CoinShape. which affect small medium large shape roundness via CoinTheme.
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
        onClick = { onSelection.invoke() }
    ) {
        Text(
            text = getDisplayResource(input = text),
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

