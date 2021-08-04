package com.example.coins.ui.listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.coins.R
import com.example.coins.theme.CoinTheme
import com.example.coins.util.getCoinImagePath
import com.example.coins.util.getFormattedCoinPrice
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun CoinListingItem(data: CoinListingItemData) {

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
                rememberCoilPainter(data.img.getCoinImagePath(),fadeIn = true),
                contentDescription = stringResource(R.string.coin_image_for, data.name),
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(50))
            )

            RankAndSymbol(name = data.name, rank = data.rank, symbol = data.symbol)

        }


        Column(Modifier.constrainAs(endBlock) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }, horizontalAlignment = Alignment.End,verticalArrangement = Arrangement.spacedBy(8.dp)) {

            Text(text = data.price, style = MaterialTheme.typography.subtitle1)
            Text(
                text = "${stringResource(R.string.mcap)} ${data.mCap}",
                style = MaterialTheme.typography.caption, textAlign = TextAlign.End
            )
        }

    }

}


@Composable
fun RankAndSymbol(name: String, rank: String, symbol: String) {
    Column {
        Text(text = name, style = MaterialTheme.typography.subtitle1)

        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
        ) {
            Surface(
                color = Color(0xFFF0F0F0),
                shape = RankShape
            ) {
                Text(
                    rank,
                    modifier = Modifier.padding(4.dp, 0.dp),
                    style = MaterialTheme.typography.caption
                )
            }

            Text(text = symbol, style = MaterialTheme.typography.caption)

        }
    }
}


private val RankShape = RoundedCornerShape(3.dp, 3.dp, 3.dp, 3.dp)
val FilterOptionShape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp)


data class CoinListingItemData(
    val img: String = "",
    val name: String = "",
    val symbol: String = "",
    val rank: String = "",
    val price: String = "",
    val mCap: String = ""
)


@Preview("Coin Listing Item")
@Composable
fun CoinListingItemPreview() {
    CoinTheme(false) {
        CoinListingItem(
            CoinListingItemData(
                "1", "Name", "NME", "12",
                getFormattedCoinPrice(coinPrice = 1210000.0),
                getFormattedCoinPrice(coinPrice = 100.0),
            )
        )
    }
}

@Preview("Coin Listing Item - Dark")
@Composable
fun CoinListingItemPreviewDark() {
    CoinTheme(true) {
        CoinListingItem(
            CoinListingItemData(
                "1", "Name", "NME", "12",
                getFormattedCoinPrice(coinPrice = 1210000.0),
                getFormattedCoinPrice(coinPrice = 100.0),
            )
        )
    }
}