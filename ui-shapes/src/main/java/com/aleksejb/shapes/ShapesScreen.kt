package com.aleksejb.shapes

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
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
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NumberOfSidesSelection(
                    modifier = Modifier.weight(1f),
                    state = state,
                    eventHandler = eventHandler
                )

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
        }

        val size = LocalConfiguration.current.screenWidthDp

        Box() {
            RegularPolygon(
                numberOfSides = state.numberOfSide,
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
                size = size.dp
            )

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "ASDASDASD",
                color = Color.White
            )
        }
    }
}

@Composable
fun RegularPolygon(
    modifier: Modifier = Modifier,
    size: Dp,
    numberOfSides: Int,
    borderStyle: DrawStyle,
    fillColor: Color
) {
    Canvas(
        modifier = modifier.size(size),
    ) {
        val centerPoint = Pair<Float, Float>(this.size.width / 2, this.size.height / 2)
        val startPoint = Pair(this.size.width / 2, 0f)
        val radius = this.size.width / 2
        val sweepAngle = (360f / numberOfSides)

        val path = Path().let {
            it.moveTo(centerPoint.first, 0f)

            val polygonEdgePoints = mutableListOf<Pair<Float, Float>>()
            polygonEdgePoints.add(startPoint)

            var currentSweepAngle = 0f
            for (i in 2..numberOfSides) {
                Log.d("TAAAG", "current iteration: $i")
                //r remains the same
                //need to add angle
                currentSweepAngle += sweepAngle

                when {
                    currentSweepAngle in 0f..90f -> {
                        Log.d("TAAAG", "currentSweepAngle: $currentSweepAngle")
                        val angle = 90 - currentSweepAngle
                        Log.d("TAAAG", "angle: $angle")
                        val angleInRadians = angle / (2 * 3.14159265359)
                        Log.d("TAAAG", "angleInRadians: $angleInRadians")
                        val x = centerPoint.first + (radius * cos(angleInRadians)).toFloat()
                        Log.d("TAAAG", "x: $x")
                        val y = centerPoint.second - (radius * sin(angleInRadians)).toFloat()
                        Log.d("TAAAG", "y: $y")
                        polygonEdgePoints.add(Pair(x, y))
                    }
                    currentSweepAngle > 90f && currentSweepAngle <= 180f -> {
                        Log.d("TAAAG", "currentSweepAngle: $currentSweepAngle")
                        val angle = 90 - (currentSweepAngle - 90)
                        Log.d("TAAAG", "angle: $angle")
                        val angleInRadians = angle / (2 * 3.14159265359)
                        Log.d("TAAAG", "angleInRadians: $angleInRadians")
                        val x = centerPoint.first + (radius * sin(angleInRadians)).toFloat()
                        Log.d("TAAAG", "x: $x")
                        val y = centerPoint.second + (radius * cos(angleInRadians)).toFloat()
                        Log.d("TAAAG", "y: $y")
                        polygonEdgePoints.add((Pair(x, y)))
                    }
                    currentSweepAngle > 180f && currentSweepAngle <= 270f -> {
                        Log.d("TAAAG", "currentSweepAngle: $currentSweepAngle")
                        val angle = currentSweepAngle - 180
                        Log.d("TAAAG", "angle: $angle")
                        val angleInRadians = angle / (2 * 3.14159265359)
                        Log.d("TAAAG", "angleInRadians: $angleInRadians")
                        val x = centerPoint.first - (radius * sin(angleInRadians)).toFloat()
                        Log.d("TAAAG", "x: $x")
                        val y = centerPoint.second + (radius * cos(angleInRadians)).toFloat()
                        Log.d("TAAAG", "y: $y")
                        polygonEdgePoints.add(Pair(x, y))
                    }
                    currentSweepAngle > 270f && currentSweepAngle <= 360 -> {
                        if (currentSweepAngle == 360f) {

                        } else {
                            Log.d("TAAAG", "currentSweepAngle: $currentSweepAngle")
                            val angle = currentSweepAngle - 270
                            Log.d("TAAAG", "angle: $angle")
                            val angleInRadians = angle / (2 * 3.14159265359)
                            Log.d("TAAAG", "angleInRadians: $angleInRadians")
                            val x = centerPoint.first - (radius * cos(angleInRadians)).toFloat()
                            Log.d("TAAAG", "x: $x")
                            val y = centerPoint.second - (radius * sin(angleInRadians)).toFloat()
                            Log.d("TAAAG", "y: $y")
                            polygonEdgePoints.add(Pair(x, y))
                        }
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
    val isNumberOfSidesSelectionExpanded = remember { mutableStateOf(false) }
    val numberOfSides = listOf(3, 4, 5, 6)

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .clickable { isNumberOfSidesSelectionExpanded.value = true }
                .padding(horizontal = dimensionResource(id = R.dimen.small_100)),
            text = "n: ${state.numberOfSide}"
        )

        DropdownMenu(
            expanded = isNumberOfSidesSelectionExpanded.value,
            onDismissRequest = { isNumberOfSidesSelectionExpanded.value = false }
        ) {
            numberOfSides.forEach {
                Text(
                    modifier = Modifier.clickable { eventHandler(ShapesEvent.OnNumberOfSidesSelected(it)) },
                    text = it.toString()
                )
            }
        }
    }
}