package com.example.coins.data.profile

data class ProfileModel(val groups : List<SettingsGroup>)

data class SettingsGroup(val mainTitle : String, val secondaryTitle :String, val settings : List<SettingItem>)

data class SettingItem(val title : String,val subtitle : String = "", val ic : String , val selectedSetting : String
,val isTypeSetting : Boolean =  false, val showIconOnly  : Boolean = false ,val isToggle : Boolean = false)


val dummyProfileModel = ProfileModel(groups = listOf(
    SettingsGroup("App","App Version 1.1.1", listOf(
        SettingItem("Launch Screen","","","Home",true),
        SettingItem("Switch to Night Mode","","","day",true,true),
        SettingItem("Language","","","English",true),
        SettingItem("Default Currencies","","","INR & BTC",true),
    )),
    SettingsGroup("Security","", listOf(
        SettingItem("Biometric Authentication","Unlock with fingerprint or facial recognition",""
            ,"false",true,true,true)
    )),
    SettingsGroup("About","", listOf(
        SettingItem("Newsletter","","",""),
        SettingItem("Privacy Policy","","",""),
        SettingItem("Terms of Use","","",""),
        SettingItem("Methodology","","",""),
    )),
    SettingsGroup("Support","", listOf(
        SettingItem("Report a Bug","","",""),
        SettingItem("Give Feedback","","",""),
        SettingItem("General Site Request Form","","",""),
        SettingItem("FAQ","","",""),
    )),

))