package com.example.coins

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.coins.databinding.ActivityMainBinding
import com.example.coins.theme.CoinTheme
import com.example.coins.ui.MainScreen
import com.example.coins.ui.discover.DiscoverViewModel
import com.example.coins.ui.home.HomeScreenViewModel
import com.example.coins.ui.listing.ListingViewModel
import com.example.coins.ui.mainDashboardScreen.DashboardViewModel
import com.example.coins.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeScreenViewModel by viewModels()
    private val viewModel: ListingViewModel by viewModels()
    private val discoverViewModel: DiscoverViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()

    @ExperimentalAnimationApi
    @ExperimentalUnitApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoinTheme {
                 MainScreen(viewModel,homeViewModel,discoverViewModel,profileViewModel,dashboardViewModel)
            }
        }
    }

}