package com.aleksejb.shapes

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleksejb.domain_core.model.OutlineType
import com.aleksejb.ui_core.R
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ShapesScreen() {
    val viewModel = hiltViewModel<ShapesViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effects) {
        viewModel.effects.collect { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    ShapesScreenContent(
        state = state,
        eventHandler = viewModel::postEvent
    )
}

@Composable
private fun ShapesScreenContent(
    state: ShapesState,
    eventHandler: (ShapesEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.normal_100))
            .fillMaxSize(),
//        verticalArrangement = Arrangement.End,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            NumberOfSidesSelection(
//                modifier = Modifier.weight(1f),
//                state = state,
//                eventHandler = eventHandler
//            )
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                OutlineTypeSelection(
//                    modifier = Modifier.weight(1f),
//                    state = state,
//                    eventHandler = eventHandler
//                )
//
//                FillSelection(
//                    modifier = Modifier.weight(1f),
//                    state = state,
//                    eventHandler = eventHandler
//                )
//            }
//
//            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal_100)))
//        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberOfSidesSelection(
                modifier = Modifier.weight(4f),
                state = state,
                eventHandler = eventHandler
            )

            Row(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .clickable { eventHandler(ShapesEvent.OnNumberOfSidesSelected(state.numberOfSide + 1)) },
                    text = "+" ,
                    fontSize = 30.sp
                )

                Text(
                    modifier = Modifier.clickable { eventHandler(ShapesEvent.OnNumberOfSidesSelected(state.numberOfSide - 1)) },
                    text = "-" ,
                    fontSize = 30.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlineTypeSelection(
                modifier = Modifier.weight(1f),
                state = state,
                eventHandler = eventHandler
            )

            FillSelection(
                modifier = Modifier.weight(1f),
                state = state,
                eventHandler = eventHandler
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal_100)))

        val size = LocalConfiguration.current.screenWidthDp

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegularPolygon(
                modifier = Modifier.fillMaxSize(),
                numberOfSides = state.numberOfSide.toInt(),
                borderStyle = if (state.outlineType == OutlineType.SOILD) {
                    Stroke(
                        width = 15f,
                        miter = 0f,
                        cap = StrokeCap.Butt,
                        join = StrokeJoin.Miter
                    )
                } else {
                    Stroke(
                        width = 15f,
                        miter = 5f,
                        cap = StrokeCap.Butt,
                        join = StrokeJoin.Miter,
                        pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(20f, 10f), phase = 0f)
                    )
                },
                fillColor = state.fillColor,
            )
        }

//        Box(
//            modifier = Modifier.size(350.dp)
//        ) {
//            RegularPolygon(
//                numberOfSides = state.numberOfSide,
//                borderStyle = if (state.outlineType == OutlineType.SOILD) {
//                    Stroke(
//                        width = 15f,
//                        miter = 0f,
//                        cap = StrokeCap.Butt,
//                        join = StrokeJoin.Miter
//                    )
//                } else {
//                    Stroke(
//                        width = 15f,
//                        miter = 5f,
//                        cap = StrokeCap.Butt,
//                        join = StrokeJoin.Miter,
//                        pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(20f, 10f), phase = 0f)
//                    )
//                },
//                fillColor = state.fillColor,
//                size = 350.dp
//            )
//
//            Text(
//                modifier = Modifier.align(Alignment.Center),
//                text = "ASDASDASD",
//                color = Color.White
//            )
//        }
    }
}

@Composable
fun RegularPolygon(
    modifier: Modifier = Modifier,
    numberOfSides: Int,
    borderStyle: DrawStyle,
    fillColor: Color
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    Canvas(
        modifier = Modifier
            .heightIn(max = screenWidthDp.dp, min = 0.dp)
            .then(modifier),
    ) {
        val centerPoint = Pair<Float, Float>(this.size.width / 2, this.size.height / 2)
        val startPoint = Pair(this.size.width / 2, 0f)
        val radius = this.size.width / 2
        val sweepAngle = (360f / numberOfSides)

        val path = Path().let {
            it.moveTo(startPoint.first, startPoint.second)

            val polygonEdgePoints = mutableListOf<Pair<Float, Float>>()

            var currentSweepAngle = 0f
            for (i in 2..numberOfSides) {
                currentSweepAngle += sweepAngle

                when {
                    currentSweepAngle in 0f..90f -> {
                        val angle = 90 - currentSweepAngle
                        val angleInRadians = angle * (3.14159265359 / 180)
                        val x = centerPoint.first + (radius * cos(angleInRadians)).toFloat()
                        val y = centerPoint.second - (radius * sin(angleInRadians)).toFloat()
                        polygonEdgePoints.add(Pair(x, y))
                    }
                    currentSweepAngle > 90f && currentSweepAngle <= 180f -> {
                        val angle = 90 - (currentSweepAngle - 90)
                        val angleInRadians = angle * (3.14159265359 / 180)
                        val x = centerPoint.first + (radius * sin(angleInRadians)).toFloat()
                        val y = centerPoint.second + (radius * cos(angleInRadians)).toFloat()
                        polygonEdgePoints.add((Pair(x, y)))
                    }
                    currentSweepAngle > 180f && currentSweepAngle <= 270f -> {
                        val angle = currentSweepAngle - 180
                        val angleInRadians = angle * (3.14159265359 / 180)
                        val x = centerPoint.first - (radius * sin(angleInRadians)).toFloat()
                        val y = centerPoint.second + (radius * cos(angleInRadians)).toFloat()
                        polygonEdgePoints.add(Pair(x, y))
                    }
                    currentSweepAngle > 270f && currentSweepAngle <= 360 -> {
                        val angle = currentSweepAngle - 270
                        val angleInRadians = angle * (3.14159265359 / 180)
                        val x = centerPoint.first - (radius * cos(angleInRadians)).toFloat()
                        val y = centerPoint.second - (radius * sin(angleInRadians)).toFloat()
                        polygonEdgePoints.add(Pair(x, y))
                    }
                }
            }

            polygonEdgePoints.forEachIndexed { index, edgePoint ->
                it.lineTo(edgePoint.first, edgePoint.second)
            }
            it.close()
            it
        }

        drawPath(path, fillColor)

        drawPath(
            path = path,
            color = Color.Blue,
            style = borderStyle
        )
    }
}

@Composable
private fun FillSelection(
    modifier: Modifier = Modifier,
    state: ShapesState,
    eventHandler: (ShapesEvent) -> Unit
) {

    val isFillSelectionExpanded = remember { mutableStateOf(false) }
    val fillOptions = listOf(
        Color.Black,
        Color.Blue,
        Color.Red,
        Color.Yellow
    )

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .clickable { isFillSelectionExpanded.value = true }
                .padding(horizontal = dimensionResource(id = R.dimen.small_100)),
            text = "Fill: ${
                when (state.fillColor) {
                    Color.Black -> "Black"
                    Color.Blue -> "Blue"
                    Color.Red -> "Red"
                    Color.Yellow -> "Yellow"
                    else -> "Error"
                }
            }"
        )

        DropdownMenu(
            expanded = isFillSelectionExpanded.value,
            onDismissRequest = { isFillSelectionExpanded.value = false }
        ) {
            fillOptions.forEach { color ->
                Text(
                    modifier = Modifier.clickable { eventHandler(ShapesEvent.OnFillSelected(color)) },
                    text = when (color) {
                        Color.Black -> "Black"
                        Color.Blue -> "Blue"
                        Color.Red -> "Red"
                        Color.Yellow -> "Yellow"
                        else -> "Error"
                    }
                )
            }
        }
    }
}

@Composable
private fun OutlineTypeSelection(
    modifier: Modifier = Modifier,
    state: ShapesState,
    eventHandler: (ShapesEvent) -> Unit
) {
    val isOutlineSelectionExpanded = remember { mutableStateOf(false) }
    val outlineOptions = listOf(OutlineType.SOILD, OutlineType.DASHED)

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .clickable { isOutlineSelectionExpanded.value = true }
                .padding(horizontal = dimensionResource(id = R.dimen.small_100)),
            text = "Outline: ${state.outlineType.toString().lowercase()}"
        )

        DropdownMenu(
            expanded = isOutlineSelectionExpanded.value,
            onDismissRequest = { isOutlineSelectionExpanded.value = false }
        ) {
            outlineOptions.forEach {
                Text(
                    modifier = Modifier.clickable { eventHandler(ShapesEvent.OnOutlineTypeSelected(it)) },
                    text = it.toString().lowercase()
                )
            }
        }
    }
}

@Composable
private fun NumberOfSidesSelection(
    modifier: Modifier = Modifier,
    state: ShapesState,
    eventHandler: (ShapesEvent) -> Unit
) {
//    val isNumberOfSidesSelectionExpanded = remember { mutableStateOf(false) }
//    val numberOfSides = listOf(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 100)

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.small_100)),
            text = "n: ${state.numberOfSide}"
        )

        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = state.numberOfSide,
            onValueChange = { eventHandler(ShapesEvent.OnNumberOfSidesSelected(it)) },
            valueRange = 3f..100f,
            steps = 100 - 3,

        )
//
//        DropdownMenu(
//            expanded = isNumberOfSidesSelectionExpanded.value,
//            onDismissRequest = { isNumberOfSidesSelectionExpanded.value = false }
//        ) {
//            numberOfSides.forEach {
//                Text(
//                    modifier = Modifier.clickable { eventHandler(ShapesEvent.OnNumberOfSidesSelected(it)) },
//                    text = it.toString()
//                )
//            }
//        }
    }
}