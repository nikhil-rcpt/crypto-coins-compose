package com.example.coins.ui

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.coins.R
import com.example.coins.ui.discover.DiscoverScreen
import com.example.coins.ui.discover.DiscoverViewModel
import com.example.coins.ui.listing.ListingViewModel
import com.example.coins.ui.listing.ListingScreen
import com.example.coins.ui.home.HomeScreen
import com.example.coins.ui.home.HomeScreenViewModel
import com.example.coins.ui.mainDashboardScreen.DashboardScreen
import com.example.coins.ui.mainDashboardScreen.DashboardViewModel
import com.example.coins.ui.profile.ProfileScreen
import com.example.coins.ui.profile.ProfileViewModel


@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(vm: ListingViewModel,hvm : HomeScreenViewModel,dvm: DiscoverViewModel
               ,profileVm : ProfileViewModel,dashboardVm: DashboardViewModel) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CoinBottomNavigation(navController, bottomNavigationItems, false)
        },
    ) {
        MainScreenNavigationConfigurations(navController, vm,hvm,dvm,profileVm,dashboardVm)
    }
}



@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(navBackStackEntry?.destination?.route)
}


@Composable
private fun CoinBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>,
    showLabel: Boolean
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.height(64.dp)
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, "") },
                label = { if (showLabel) Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false, // This hides the title for the unselected items
                onClick = {
                    navController.navigate(screen.route) {

                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items

//                        popUpTo = navController.graph.startDestination


                        popUpTo(navController.graph.startDestinationId)

                        // Avoid multiple copies of the same destination when
                        // reselecting the same item

                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalUnitApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    vm: ListingViewModel,
    hvm : HomeScreenViewModel,
    discoverVm : DiscoverViewModel,
    profileVm : ProfileViewModel,
    dashboardVm : DashboardViewModel
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen("home",hvm)
        }
        composable(BottomNavigationScreens.Listing.route) {
            ListingScreen("list", vm)
        }
        composable(BottomNavigationScreens.Dashboard.route) {
//            DashboardScreen("dashboard",dashboardVm)
        }
        composable(BottomNavigationScreens.Discover.route) {
            DiscoverScreen("discover", discoverVm)
        }
        composable(BottomNavigationScreens.Settings.route) {
            ProfileScreen("settings",profileVm)
        }
    }
}



val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Listing,
    BottomNavigationScreens.Dashboard,
    BottomNavigationScreens.Discover,
    BottomNavigationScreens.Settings
)


sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Home : BottomNavigationScreens("Home", R.string.home_route, Icons.Filled.Home)
    object Listing :
        BottomNavigationScreens("Listing", R.string.listing_route, Icons.Filled.Leaderboard)

    object Dashboard :
        BottomNavigationScreens("Dashboard", R.string.dashboard_route, Icons.Filled.PieChart)

    object Discover :
        BottomNavigationScreens("Discover", R.string.discover_route, Icons.Filled.Explore)

    object Settings :
        BottomNavigationScreens("Settings", R.string.settings_route, Icons.Filled.MoreHoriz)
}

