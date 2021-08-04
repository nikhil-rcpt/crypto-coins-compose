package com.example.coins.ui.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.coins.R
import com.example.coins.data.profile.SettingItem
import com.example.coins.data.profile.SettingsGroup


@ExperimentalUnitApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ProfileScreen(name: String, vm: ProfileViewModel) {

    val viewState by vm.liveData.observeAsState()


    viewState?.let { state ->

        LazyColumn(
            Modifier
                .background(colorResource(id = R.color.profile_gray))
        ) {

            stickyHeader {

                Column(
                    modifier = Modifier
                        .background(color = colorResource(R.color.white))
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "CoinMarketCap",
                        style = MaterialTheme.typography.h5,
                        color = Color.Black, modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }


            item {
                Spacer(modifier = Modifier.height(16.dp))

                SocialMediaCard(state.socialMediaLinks)

                Spacer(Modifier.height(16.dp))

                LoginCard()

                Spacer(Modifier.height(16.dp))

                state.profileSettingsGroup.groups.forEach { group ->
                    SettingsGroup(group)
                }
            }
        }
    }
}


@ExperimentalUnitApi
@Composable
fun LoginCard() {
    Card(

        elevation = 1.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
        backgroundColor = colorResource(R.color.profile_blue)


    ) {

        Column(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 24.dp),
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                "Get more features!",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.white)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Create an account and get features like custom price alerts, watchlist sync & more! Don't worry, it's quick, secure and always free!",
                style = MaterialTheme.typography.caption, lineHeight = TextUnit(
                    20F,
                    TextUnitType.Sp
                ),
                color = colorResource(id = R.color.white), letterSpacing = 0.sp
            )
            Spacer(Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = { },
                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
//                                    .fillMaxWidth(0.5f)
//                                    .padding(horizontal = 8.dp)
                        .weight(1f), colors = ButtonDefaults.buttonColors(
                        colorResource(id = R.color.white),
                        colorResource(id = R.color.black),
                    ), contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        "Login",
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Button(

                    onClick = { },
                    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
//                                    .fillMaxWidth(0.5f)
//                                    .padding(end = 8.dp)
                        .weight(1f), colors = ButtonDefaults.buttonColors(
                        colorResource(id = R.color.white),
                        colorResource(id = R.color.black),
                    ), contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        "Create an account",
                        style = MaterialTheme.typography.caption.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
        }

    }
}


@Composable
fun SocialMediaCard(socialLinks: List<SocialMediaType>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        socialLinks.forEach {
            Surface(
                shape = RoundedCornerShape(50),
                elevation = 2.dp,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            ) {
                Icon(
                    painter = painterResource(it.res),
                    "",
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .padding(6.dp),
                    tint = colorResource(id = R.color.icon_light_gray)
                )
            }

        }
    }
}

@Composable
fun SettingsGroup(group: SettingsGroup) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            group.mainTitle,
            style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Bold),
        )

        if (group.secondaryTitle.isNotBlank())
            Text(
                group.secondaryTitle, style = MaterialTheme.typography.caption,
            )
    }

    Spacer(Modifier.height(4.dp))

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
    ) {

        Column {
            group.settings.forEachIndexed { i, settingItem ->
                SettingItem(i != group.settings.size - 1, settingItem)
            }
        }

    }
    Spacer(Modifier.height(16.dp))
}

@Composable
fun SettingItem(showDivider: Boolean, settingItem: SettingItem) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                settingItem.title,
                style = MaterialTheme.typography.caption.copy(
                    color = colorResource(
                        id = R.color.black
                    )
                )
            )
            if (settingItem.subtitle.isNotEmpty()) {

                Text(
                    settingItem.subtitle,
                    style = MaterialTheme.typography.overline.copy(
                        color = colorResource(
                            id = R.color.text_light_gray
                        ), letterSpacing = 0.sp
                    ),
                )
            }
        }


        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            var res = R.drawable.ic_arrow_right
            var tint = R.color.icon_light_gray
            var size = 28.dp
            if (settingItem.title.contains("night", true)) {
                res = R.drawable.ic_profile_dark_mode_toggle
                tint = R.color.icon_super_light_gray
                size = 20.dp
            }

            if (!settingItem.showIconOnly) {
                Text(
                    settingItem.selectedSetting,
                    style = MaterialTheme.typography.caption.copy(
                        color = colorResource(
                            id = R.color.text_light_gray
                        )
                    ),
                )
            }


            if (settingItem.isToggle) {
                Switch(
                    checked = false,
                    onCheckedChange = { }
                )
            } else {
                Icon(
                    painter = painterResource(res),
                    "",
                    modifier = Modifier
                        .width(size)
                        .height(size),
                    tint = colorResource(id = tint),

                    )
            }


        }
    }

    if (showDivider) Divider(color = colorResource(id = R.color.light_button_gray_bg))

}

