package com.example.coins.ui.customUi

import android.graphics.Paint
import android.graphics.PointF
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.example.coins.R

private const val GRAPH_GUIDE_COUNT = 3
private fun getMinMaxMg(data: List<Int>) : Pair<Int,Int>{
    if(data.isEmpty())  return Pair(0,0)
    var min = data.first()
    var max = data.first()
    data.forEach {
        if (it>max)max = it
        if(it<min)min = it
    }
    return Pair(min,max)
}

private fun createPointsMg(data: List<Int>, size: Size?,minMax: Pair<Int, Int>): List<PointF> {
    size ?: return listOf()
    val widthBetweenEachPoint = (size.width / (data.size-1))
    val boxHeight = size.height
    val pointsO = mutableListOf<PointF>()
    val maxData = minMax.second
    val minData = minMax.first
    val adjustedMax = maxData-minData
    val heightRatio = boxHeight/adjustedMax

    data.forEachIndexed {i,v->
        val fh = boxHeight- (heightRatio*(v-minData))
        pointsO.add(PointF(i *widthBetweenEachPoint,fh))
    }
    return pointsO
}

private fun isGainMg(data: List<Int>) : Boolean {
    return data.last()>data.first()
}

private fun calculateConnectionPointsForBezierCurveMg(points : List<PointF>): Triple<List<PointF>, List<PointF>, List<PointF>> {
    Log.i("graph","calculateConnectionPointsForBezierCurve ")

    val conPoint1 = mutableListOf<PointF>()
    val conPoint2 = mutableListOf<PointF>()
    try {
        for (i in 1 until points.size) {
            conPoint1.add(PointF((points[i].x + points[i - 1].x) / 2, points[i - 1].y))
            conPoint2.add(PointF((points[i].x + points[i - 1].x) / 2, points[i].y))
        }
    } catch (e: Exception) {
    }

    return Triple(points,conPoint1,conPoint2)
}


@Composable
fun MainDashboardCoinGraph(dataSet : List<Int> = listOf(10,11,15,9,10,12),styleThin:Boolean = false) {
    //todo take properties in composable
    val colorPathBorderGain =  colorResource(R.color.graph_gain)
    val colorGraphGuide =  colorResource(R.color.graph_guide)
    val colorPathBorderGainGradient =  colorResource(R.color.graph_gain_gradient)
    val colorPathBorderLossGradient =  colorResource(R.color.graph_loss_gradient)
    val colorPathBorderLoss =  colorResource(R.color.graph_loss)
    val isGain by remember { mutableStateOf(isGainMg(dataSet)) }
    val graphGuideTextPaintSize =  dimensionResource(R.dimen.graph_guide_text_size).value
    val graphGuideTextPaint by remember { mutableStateOf(Paint().also { it.textSize = graphGuideTextPaintSize}) }
    val minMax by remember { mutableStateOf(getMinMaxMg(dataSet)) }
    val colorPathBorder by remember { mutableStateOf(  if(isGain)colorPathBorderGain else colorPathBorderLoss    ) }
    val colorPathBorderGradient by remember { mutableStateOf(  if(isGain)colorPathBorderGainGradient else colorPathBorderLossGradient    ) }
    val colorWhite = colorResource(id = R.color.white)
    var size by remember { mutableStateOf<Size?>(null) }
    var points by remember { mutableStateOf(createPointsMg(dataSet,size,minMax)  ) }
    var data by remember { mutableStateOf( calculateConnectionPointsForBezierCurveMg(points)) }
    var finalData by remember { mutableStateOf(drawBezierCurveMg(data.first,data.second,data.third,size)) }
    var graphGuideLines by remember { mutableStateOf(getGraphGuideLines(size,minMax)) }
    val strokeWidth by remember { mutableStateOf(if(styleThin) 4f else 5F )}
    Canvas(modifier= Modifier.fillMaxSize()){

        Log.i("graph : ondraw ","here size=$size ${this.size}")

//        for(i in 0..5){
//            drawLine(Color.Black, Offset(size?.height!!/2.5f,0f), Offset(size?.height!!/2.5f,size?.width!!.toFloat()))
//    }
        if(size!=null) {
            drawPath(finalData.first, Brush.verticalGradient(listOf(colorPathBorderGradient, colorWhite)))
            drawPath(finalData.second, colorPathBorder, style = Stroke(width = strokeWidth))

            graphGuideLines.forEach {
                drawLine(colorGraphGuide, Offset(0f,it.value.toFloat()), Offset(size?.width!!,it.value.toFloat()))
            }
            drawIntoCanvas {canvas->
                graphGuideLines.forEach {
                    canvas.nativeCanvas.drawText("\u20B9 ${it.key}",16f,it.value.toFloat()+graphGuideTextPaintSize, graphGuideTextPaint)
                }
            }
        }

        if(size!=this.size) {
            size=this.size
            points=createPointsMg(dataSet,size,minMax);
            data=calculateConnectionPointsForBezierCurveMg(points)
            finalData=drawBezierCurveMg(data.first,data.second,data.third,size)
            graphGuideLines= getGraphGuideLines(size,minMax)
        }
    }
}

fun getGraphGuideLines(size: Size?, minMax: Pair<Int, Int>): Map<Int,Int> {
    size ?: return hashMapOf()
    val divHeight =  size.height/ GRAPH_GUIDE_COUNT
    val heightValue = (minMax.second-minMax.first).toFloat()/ (GRAPH_GUIDE_COUNT).toFloat()
    val map= mutableMapOf<Int,Int>()
    for (i in GRAPH_GUIDE_COUNT downTo 0){
        //key : graph line value from dataset, value : graph line height relative to key
        map[(minMax.first+(i*heightValue)).toInt()]=(GRAPH_GUIDE_COUNT-i)*divHeight.toInt()
    }
    return map.toMap()

}



private fun drawBezierCurveMg(points : List<PointF>, conPoint1 : List<PointF>
                            , conPoint2 :List<PointF>, size : Size?): Pair<Path, Path> {

    size ?: return Pair(Path(), Path())
    val path  = Path()
    val borderPath  = Path()
    val width = size.width
    val height = size.height

    try {

        if (points.isEmpty() && conPoint1.isEmpty() && conPoint2.isEmpty()) return Pair(
            Path(),
            Path()
        )

        path.reset()
        path.moveTo(points.first().x, points.first().y)

        for (i in 1 until points.size) {
            path.cubicTo(
                conPoint1[i - 1].x, conPoint1[i - 1].y, conPoint2[i - 1].x, conPoint2[i - 1].y,
                points[i].x, points[i].y
            )
        }

        borderPath.addPath(path)
        path.lineTo(width, height)
        path.lineTo(0f, height)
        return Pair(path,borderPath)

    } catch (e: Exception) {
        return Pair(Path(), Path())
    }
}