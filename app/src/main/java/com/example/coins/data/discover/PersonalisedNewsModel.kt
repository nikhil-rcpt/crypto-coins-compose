package com.example.coins.data.discover

import com.example.coins.R

data class PersonalisedModelGroup(val groups: List<PersonalisedNewsModel>)
data class PersonalisedNewsModel(
    val name: String = "",
    val list: List<PersonalisedNewsItem> = listOf(),val tempSrc :Int = 0
)

data class PersonalisedNewsItem(
    val title: String,
    val source: String,
    val time: String,
    val imgSrc: String,
    val imgTemp: Int,
)


var dummyTrending = PersonalisedNewsModel(
    "Trending",
    listOf(
        PersonalisedNewsItem(
            "El Salvador Bitcoin move will put pressure on network: JPMorgan",
            "U.Today",
            "2h ago",
            "",
            R.drawable.coin_banner
        ),
        PersonalisedNewsItem(
            "10 Biggest Ethereum Whales Buy Even More ETH, No Holding 20.58%",
            "Feed - Cryptopotato.Com",
            "3h ago",
            "", R.drawable.crypto_img_1

        ),
        PersonalisedNewsItem(
            "Uniswap v3 launches Optimistic Ethereum layer two scaling in alpha",
            "CoinTelegraph",
            "34h ago",
            "", R.drawable.crypto_img_2
        )
    ),R.drawable.trending_fire
)

var dummyEther = PersonalisedNewsModel(
    "Ethereum",
    listOf(
        PersonalisedNewsItem(
            "Biggest Ethereum Whales Buy Even More eth, Now Holding 20.58%: Data",
            "U.Today",
            "2h ago",
            "", R.drawable.crypto_img_1
        ),
        PersonalisedNewsItem(
            "Brasil's SEC Approves First Ethereum ETF in Latin America",
            "Feed - Cryptopotato.Com",
            "3h ago",
            "", R.drawable.crypto_img_2
        ),
        PersonalisedNewsItem(
            "Uniswap v3 launches Optimistic Ethereum layer two scaling in alpha",
            "CoinTelegraph",
            "34h ago",
            "", R.drawable.coin_banner
        )
    ),R.drawable.coin_temp_btc
)

var dummyBitcoin = PersonalisedNewsModel(
    "Bitcoin",
    listOf(
        PersonalisedNewsItem(
            "Visa set to approve Australia's first card for spending Bitcon",
            "U.Today",
            "2h ago",
            "",R.drawable.crypto_img_1
        ),
        PersonalisedNewsItem(
            "Bitcoin Dumps to a 17-Day Low Below $32K as Crypto Markets Lost $80B (Market Watch)",
            "CoinTelegraph",
            "3h ago",
            "",R.drawable.coin_banner
        ),
        PersonalisedNewsItem(
            "Uniswap v3 launches Optimistic Ethereum layer two scaling in alpha",
            "Feed - Cryptopotato.Com",
            "34h ago",
            "",R.drawable.crypto_img_2
        )
    ),R.drawable.coin_temp_btc
)
var personalisedGroup = PersonalisedModelGroup(listOf(dummyEther, dummyBitcoin))

