package com.example.coins.ui.discover.latestNews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coins.R
import com.example.coins.data.discover.PersonalisedNewsItem
import com.example.coins.data.discover.PersonalisedNewsModel
import com.example.coins.ui.discover.DiscoverViewModel
import com.example.coins.ui.discover.DiscoverViewState
import com.google.accompanist.coil.rememberCoilPainter


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun LatestNewsSection(name: String, vm: DiscoverViewModel, state: DiscoverViewState) {


    Spacer(Modifier.height(16.dp))



    if (state.trending.list.isNotEmpty()) {
        DiscoverListHeader(
            R.drawable.trending_fire, state.trending.name,
            R.string.popular_and_breaking_news
        )

        Spacer(Modifier.height(16.dp))
        val listState = rememberLazyListState()

        BoxWithConstraints(
            Modifier
                .fillMaxWidth()

        ) {


            LazyRow(state = listState) {
                items(state.trending.list) {
                    DiscoverNewsCardItem(maxWidth, it)
                }
            }


        }

        Spacer(Modifier.height(16.dp))


        LazyRow(
            horizontalArrangement =

            Arrangement.spacedBy(
                6.dp,
                Alignment.CenterHorizontally

            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.trending.list.size) {

                Circle(
                    if (it == listState.firstVisibleItemIndex) colorResource(R.color.black) else colorResource(
                        R.color.light_button_gray_bg
                    ),
                    Modifier.size(6.dp)
                )
            }
        }

    }


    Spacer(Modifier.height(32.dp))


    state.personalisedNewsGroup.groups.forEach {
        PersonalizedNewsBlock(it)
    }

    Spacer(Modifier.height(64.dp))


}


@Composable
fun Circle(
    color: Color,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.composed {

            clip(CircleShape)
                .background(color)
        }
    )
}


@Composable
fun PersonalizedNewsBlock(item: PersonalisedNewsModel) {

    DiscoverListHeader(
        item.tempSrc, item.name,
        R.string.based_on_your_portfolio
    )

    Spacer(Modifier.height(16.dp))


    BoxWithConstraints(
        Modifier
            .fillMaxWidth()

    ) {

        val mw = maxWidth

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            item.list.forEach {
                DiscoverNewsCardItemSmall(mw, it)
            }
        }
    }


    Spacer(Modifier.height(16.dp))

    Button(
        onClick = { },
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.light_button_gray_bg),
            colorResource(id = R.color.black),
        ), contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        Text("More Ethereum")
    }

    Spacer(Modifier.height(32.dp))

}


@Composable
private fun DiscoverNewsCardItemSmall(width: Dp, item: PersonalisedNewsItem) {
    val imageWidth = width / 4f
    Row(verticalAlignment = Alignment.CenterVertically) {


        Image(
            painter = painterResource(item.imgTemp),
            "",
            modifier = Modifier
                .width(imageWidth)
                .height(imageWidth)
                .clip(RoundedCornerShape(16)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f), verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
                maxLines = 3, overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberCoilPainter(R.drawable.trending_fire),
                    contentDescription = "Trending Icons",
                    modifier = Modifier
//                            .padding(start=16.dp)
                        .size(
                            24.dp
                        )
                )


                Text(
                    text = "${item.source} - ${item.time}",
                    style = MaterialTheme.typography.caption,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )

            }
        }

    }

}


@Composable
fun DiscoverListHeader(icon: Int, title: String, subtitle: Int) {

    Row(
        Modifier
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {


        Image(
            painter = rememberCoilPainter(icon),
            contentDescription = "Trending Icons",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(
                    24.dp
                )
        )

        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
        )

        Text(
            text = stringResource(id = R.string.see_all),
            style = MaterialTheme.typography.body2.copy(color = colorResource(R.color.text_blue)),
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(horizontal = 16.dp)
        )
    }

    Spacer(Modifier.height(8.dp))


    Text(
        text = stringResource(subtitle),
        style = MaterialTheme.typography.subtitle2,
        color = Color.Black, modifier = Modifier.padding(start = 16.dp)

    )
}


@Composable
fun DiscoverNewsCardItem(width: Dp, item: PersonalisedNewsItem) {
    Card(

        elevation = 1.dp, shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(width = width)
            .padding(start = 16.dp, end = 16.dp),


        ) {
        Column() {


            Image(
                painter = painterResource(item.imgTemp),
                "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
//                        .clip(RoundedCornerShape(50))
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black,
                    maxLines = 3, overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberCoilPainter(R.drawable.trending_fire),
                        contentDescription = "Trending Icons",
                        modifier = Modifier
//                            .padding(start=16.dp)
                            .size(
                                24.dp
                            )
                    )


                    Text(
                        text = "${item.source} - ${item.time}",
                        style = MaterialTheme.typography.caption,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 4.dp),
                    )

                }
            }
        }
    }

}


