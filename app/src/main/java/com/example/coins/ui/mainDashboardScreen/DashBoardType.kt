package com.example.coins.ui.mainDashboardScreen

import androidx.annotation.StringRes
import com.example.coins.R


sealed class GraphCategory(val id: String,@StringRes val res : Int) {
     object LineChart : GraphCategory("Line Chart", R.string.line_chart)
     object PieChart: GraphCategory("Pie Chart", R.string.pie_chart)
     object Statistics : GraphCategory("Statistics", R.string.statistics)
}

sealed class GraphRange(val id: String,@StringRes val res : Int) {
     object _24H : GraphRange("24h", R.string._24h)
     object _7d: GraphRange("7d", R.string._7d)
     object _30d : GraphRange("30d", R.string._30d)
     object _60d : GraphRange("60d", R.string._60d)
     object _90d : GraphRange("90d", R.string._90d)
     object _all : GraphRange("all", R.string.all)
}

sealed class AssetView(val id : String){
     object Value :AssetView("value")
     object Percentage :AssetView("percentage")
}



val dummyGraphDataPoints: List<Int> = listOf(
     1000, 1200, 1100, 1200, 1300, 1600, 1500, 1400, 1600, 1700, 1800, 1900,
     2000, 2500, 2400, 2400, 2200, 2100, 1900, 1800, 1700, 1600, 1800
     , 1700, 1800, 1800, 1900
)