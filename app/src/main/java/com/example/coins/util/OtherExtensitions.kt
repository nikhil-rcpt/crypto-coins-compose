package com.example.coins.util

import com.example.coins.network.NetworkConstants


fun String.getCoinImagePath() : String =  "${NetworkConstants.IMAGE_PATH}$this.png"