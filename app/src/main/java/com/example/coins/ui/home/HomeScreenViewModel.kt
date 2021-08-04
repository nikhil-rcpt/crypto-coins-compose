package com.example.coins.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.coins.base.BaseViewModel
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.network.CoinUseCase1
import com.example.coins.ui.home.HomeCoinListType.*
import com.example.coins.util.getFormattedCoinPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
public class HomeScreenViewModel @Inject constructor(private val coinUseCase: CoinUseCase1) :
    BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        viewModelScope.launch {
            this.launchSetState { copy(progress = true) }
            val a = coinUseCase.fetchTestLatestListing().get() ?: LatestCryptoListing(listOf())
            Log.i("mylogger", "got test result")
            this.launchSetState {
//                a.data?.let {
//                    this.HomeCoinList[0].updateItems(it.filterNotNull())
//                    copy(latestCryptoListing = a,progress = false)
//                } ?: copy(progress = false)
                a.data?.let {
//                    val items = it.filterNotNull().map { getFormattedCoinPrice(coinPrice = it.quote?.inr?.price) }
                    val items = it.filterNotNull()
                    copy(
                        latestCryptoListing = a,
                        homeCoinList = listOf(TopCoins(items), GainersAndLosers(items)),
                        progress = false
                    )
                } ?: copy(progress = false)
            }
        }
    }


}