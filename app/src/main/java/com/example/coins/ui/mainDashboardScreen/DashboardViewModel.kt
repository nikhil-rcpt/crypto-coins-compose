package com.example.coins.ui.mainDashboardScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.coins.base.BaseViewModel
import com.example.coins.data.latestListing.LatestCryptoListing
import com.example.coins.network.CoinUseCase1
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
public class DashboardViewModel @Inject constructor(private val coinUseCase: CoinUseCase1) :
    BaseViewModel<DashboardViewState>(DashboardViewState()) {


    init {
        viewModelScope.launch {
            this.launchSetState { copy(progress = true) }
            val a = coinUseCase.fetchTestLatestListing().get() ?: LatestCryptoListing(listOf())
            Log.i("mylogger", "got test result")
            this.launchSetState { copy(latestCryptoListing = a, progress = false) }
        }
    }

    fun onGraphOptionSelection(it: GraphCategory) {
        viewModelScope.launchSetState {
            copy(selectedGraphOption = it)
        }
    }
    fun onGraphRangeOptionSelection(it: GraphRange) {
        viewModelScope.launchSetState {
            copy(selectedGraphRangeOption = it)
        }
    }

    fun setSelectedAssetView(it : AssetView) {
       viewModelScope.launchSetState {
           copy(assetView = it)
       }
    }
}