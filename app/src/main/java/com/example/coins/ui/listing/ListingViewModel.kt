package com.example.coins.ui.listing

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.coins.base.BaseViewModel
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.network.CoinUseCase1
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
public class ListingViewModel @Inject constructor(private val coinUseCase: CoinUseCase1) :
    BaseViewModel<ListingViewState>(ListingViewState()) {

    init {
        viewModelScope.launch {
            this.launchSetState { copy(progress = true) }
            val a = coinUseCase.fetchTestLatestListing().get() ?: LatestCryptoListing(listOf())
            Log.i("mylogger", "got test result")
            this.launchSetState { copy(latestCryptoListing = a, progress = false) }
        }
    }

    fun latestListing() {
        viewModelScope.launch {
            this.launchSetState { copy(progress = true) }
            val a = coinUseCase.fetchLatestListing()
            Log.i("mylogger", "got result")
            this.launchSetState { copy(query = "ab", progress = false) }
        }
    }

    fun onListingTabSelection(cat : CoinListingCategory){
        viewModelScope.launchSetState {
            copy(selectedListingTab  = cat)
        }
    }
}