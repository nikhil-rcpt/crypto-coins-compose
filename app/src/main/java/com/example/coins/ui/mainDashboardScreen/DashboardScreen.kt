package com.example.coins.ui.mainDashboardScreen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coins.R
import com.example.coins.ui.customUi.MainDashboardCoinGraph

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun DashboardScreen(name: String, vm: DashboardViewModel) {

    val viewState by vm.liveData.observeAsState()



    viewState?.let { state ->

        LazyColumn(
            Modifier
                .background(MaterialTheme.colors.background)
//                .verticalScroll(rememberScrollState())
        ) {


            stickyHeader() {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    backgroundColor = colorResource(id = R.color.black),
                    elevation = 1.dp,
                    shape = RoundedCornerShape(0.dp), modifier = Modifier.height(80.dp)
                ) {


                    Box() {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(colorResource(id = R.color.white))
                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(16.dp, 28.dp),
                                text = stringResource(id = R.string.home),
                                style = MaterialTheme.typography.body2
                            )
                        }



                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 28.dp)
                        ) {

                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                            )

                            Icon(
                                imageVector = Icons.Filled.Search,
                                "Search Icon",
                                modifier = Modifier.padding(end = 16.dp),
                                tint = colorResource(id = R.color.icon_super_light_gray)
                            )

                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                "Notification Icon",
                                tint = colorResource(id = R.color.icon_super_light_gray)

                            )

                        }
                    }


                }

            }
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(300.dp), contentAlignment = Alignment.Center
                ) {

                    MainDashboardCoinGraph(styleThin = true,
                        dataSet = listOf(10,12,11,12,13,16,15,14,16,17,18,19,20,25,24
                            ,24,22,21,19,18,17,16,18,17,18,18,19))
                }
            }
        }


    }


}
