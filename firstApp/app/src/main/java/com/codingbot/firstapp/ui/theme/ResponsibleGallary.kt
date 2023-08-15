package com.codingbot.firstapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.random.Random


val SIZES = listOf(25.dp, 50.dp, 75.dp, 100.dp, 150.dp)
val COLORS = listOf(Color.Green, Color.Red, Color.Blue, Color.Yellow)
@Composable
fun ResponsiveGallary(maxWidth:Dp = 400.dp,modifier: Modifier = Modifier, content:@Composable () -> Unit){
    Layout(content,modifier){
        measureable,constraints ->
        var currentRow = 0
        var rowPositions: MutableList<Int> = mutableListOf()
        var rowToHeight:MutableMap<Int,Int> = mutableMapOf()
        var maxWidthSoFar = 0
        var totalHeight = 0
        var currentWidth = 0

        var firstSeen = false


        val placeables = measureable.map{ measurable ->
            val placeable = measurable.measure(constraints)

            if (!firstSeen){
                maxWidthSoFar = placeable.width
                totalHeight = placeable.height
                firstSeen = true
            }

            if (currentWidth + placeable.width > maxWidth.toPx()){
                totalHeight += rowToHeight[currentRow]!!
                maxWidthSoFar = max(maxWidthSoFar,currentWidth)
                currentRow++
                currentWidth = placeable.width
            } else {
                currentWidth += placeable.width
            }
            rowToHeight[currentRow] = max(rowToHeight.getOrDefault(currentRow, 0),placeable.height)
            rowPositions.add(currentRow)
            placeable
        }


        var x = 0
        var y = 0

        layout(maxWidthSoFar,totalHeight){
            placeables.mapIndexed { index, placeable ->

                val row = rowPositions[index]
                placeable.placeRelative(x, y)
                if (index >= placeables.size - 1) {
                    return@layout
                }
                if (row != rowPositions[index + 1]) {
                    x = 0
                    y += rowToHeight[row]!!
                } else {
                    x+= placeable.width
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewResponsiveGallary(){

    FirstAppTheme{
        ResponsiveGallary(maxWidth=LocalConfiguration.current.screenWidthDp.dp){
            for (i in 0 .. 30){
                val randomColor = COLORS[Random.nextInt(COLORS.size)]
                val randomWidth = SIZES[Random.nextInt(COLORS.size)]
                val randomHeight = SIZES[Random.nextInt(COLORS.size)]
                Box(
                    Modifier

                        .size(randomWidth,randomHeight)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(randomColor)

                )
            }
        }
    }
}