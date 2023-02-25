package com.aleksejb.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleksejb.domain_core.model.OutlineType
import com.aleksejb.ui_core.R

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
        verticalArrangement = Arrangement.SpaceEvenly
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

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(Color.Black),
        ) {
            //draw here
        }
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