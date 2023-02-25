package com.aleksejb.shapes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleksejb.ui_core.R

@Composable
fun ShapesScreen(

) {
    val viewModel = hiltViewModel<ShapesViewModel>()
    val state = viewModel.state.collectAsState()

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
    state: State<ShapesState>,
    eventHandler: (ShapesEvent) -> Unit
) {
    val isNumberOfSidesSelectionExpanded = remember { mutableStateOf(false) }
    val numberOfSides = listOf(3, 4, 5, 6)
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_default))
            .fillMaxSize()
    ) {
        DropdownMenu(
            expanded = isNumberOfSidesSelectionExpanded.value,
            onDismissRequest = { isNumberOfSidesSelectionExpanded.value = false }
        ) {
            numberOfSides.forEach {
                Text(
                    text = it.toString()
                )
            }
        }
    }
}