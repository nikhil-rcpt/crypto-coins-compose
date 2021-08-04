package com.example.coins.ui.discover

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.coins.base.BaseViewModel
import com.example.coins.data.discover.PersonalisedModelGroup
import com.example.coins.data.discover.PersonalisedNewsItem
import com.example.coins.data.discover.PersonalisedNewsModel
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.network.CoinUseCase1
import com.example.coins.ui.listing.CoinListingCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
public class DiscoverViewModel @Inject constructor(private val coinUseCase: CoinUseCase1) :
    BaseViewModel<DiscoverViewState>(DiscoverViewState()) {

    init {
        viewModelScope.launch {
            Log.i("discover", "launch latest News")
            getLatestNews()
            Log.i("discover", "launch after latest News")
            getTrendingNews()
            Log.i("discover", "launch after trending News")

        }
    }

    //
    fun getLatestNews() {
        viewModelScope.launch {
            Log.i("discover", "launch getLatestNews ")
            this.launchSetState { copy(progress = true) }
            val a = coinUseCase.fetchNewsItems().get() ?: PersonalisedModelGroup(listOf())
            this.launchSetState { copy(personalisedNewsGroup = a, progress = false) }
            Log.i("discover", "launch after getLatestNews ")
        }
    }

    fun getTrendingNews() {
        viewModelScope.launch {
            Log.i("discover", "launch getTrendingNews ")
            this.launchSetState { copy(progress = true) }
            val a =
                coinUseCase.fetchTrendingNewsItems().get() ?: PersonalisedNewsModel("", listOf(),0)
            this.launchSetState { copy(trending = a, progress = false) }
            Log.i("discover", "launch after getTrendingNews ")

        }
    }
//    fun latestListing() {
//        viewModelScope.launch {
//            this.launchSetState { copy(progress = true) }
//            val a = coinUseCase.fetchLatestListing()
//            Log.i("mylogger", "got result")
//            this.launchSetState { copy(query = "ab", progress = false) }
//        }
//    }


    fun onListingTabSelection(cat: DiscoverListingCategory) {
        viewModelScope.launchSetState {
            copy(selectedListingTab = cat)
        }
    }
}