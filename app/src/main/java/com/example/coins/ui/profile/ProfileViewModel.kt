package com.example.coins.ui.profile

import androidx.lifecycle.viewModelScope
import com.example.coins.base.BaseViewModel
import com.example.coins.data.discover.PersonalisedModelGroup
import com.example.coins.data.profile.ProfileModel
import com.example.coins.network.CoinUseCase1
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
public class ProfileViewModel @Inject constructor(private val coinUseCase: CoinUseCase1) :
    BaseViewModel<ProfileViewState>(ProfileViewState()) {

    init {
        viewModelScope.launch {
            getProfileSettings(this)
        }
    }

    //
    private suspend fun getProfileSettings(scope: CoroutineScope) {
        scope.launchSetState { copy(progress = true) }
        val a = coinUseCase.fetchSettings().get() ?: ProfileModel(listOf())
        scope.launchSetState { copy(profileSettingsGroup = a, progress = false) }

    }

}


//    fun onListingTabSelection(cat: DiscoverListingCategory) {
//        viewModelScope.launchSetState {
//            copy(selectedListingTab = cat)
//        }
//    }
//}