package com.example.workenvironment.presentation

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workenvironment.ui.theme.Purple200
import com.example.workenvironment.ui.theme.Purple700
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileData(){
    
}


@Composable
fun Statistics() {

    val list = listOf(1) + ((0..100).map { it })
    val animationProgress = remember { androidx.compose.animation.core.Animatable(0f) }
    Box(
        modifier = Modifier
            .background(Purple200)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier
            .padding(8.dp)
            .aspectRatio(3 / 2f)
            .fillMaxSize()
            .drawWithCache {
                val path = generatePath(list, size)
                val fillPAth = Path()
                fillPAth.addPath(path)
                fillPAth.lineTo(size.width, size.height)
                fillPAth.lineTo(0f, size.height)
                fillPAth.close()
                val brush = Brush.verticalGradient(
                    listOf(
                        Color.Green.copy(alpha = 0.4f),
                        Color.Transparent
                    )
                )

                onDrawBehind {
                    drawPath(path, Color.Green, style = Stroke(2.dp.toPx()))
                    drawPath(fillPAth, brush = brush, style = Fill)
                }
            }
        )
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)
                .fillMaxSize()
        ) {
            val barWidthPx = 1.dp.toPx()
            drawRect(color = Purple700, style = Stroke(barWidthPx), topLeft = Offset(0f, 10f))
            val verticalLines = 4
            val verticalSize = size.width / (verticalLines + 1)
            repeat(verticalLines) { i ->
                val startX = verticalSize * (i + 1)
                drawLine(
                    Purple700,
                    start = Offset(startX, 0f),
                    end = Offset(startX, size.height),
                    strokeWidth = barWidthPx
                )

            }
            val horizontalLines = 4
            val horizontalSize = size.height / (horizontalLines + 1)
            repeat(horizontalLines) { i ->
                val startY = horizontalSize * (i + 1)
                drawLine(
                    Purple700,
                    start = Offset(0f, startY),
                    end = Offset(size.width, startY),
                    strokeWidth = barWidthPx
                )

            }
        }
    }
}

fun generatePath(data: List<Int>, size: Size): Path {
    val path = Path()
    data.forEachIndexed { index, t ->
        val x = index
        val y = t
        path.lineTo(x.toFloat(), y.toFloat())

    }
    return path
}

@Composable
@Preview(showBackground = true)
fun Preview() {
    Statistics()
}