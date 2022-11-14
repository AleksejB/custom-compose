package com.aleksejb.ui_chess_timer.chesstimeselection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleksejb.domain_core.model.TimeSelection
import com.aleksejb.ui_core.R

@Composable
fun ChessTimerSelectionScreen(
    navigateToTimer: (selection: TimeSelection) -> Unit
) {
    val viewModel = hiltViewModel<ChessTimerViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effects) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ChessTimerSelectionEffect.NavigateToTimer -> {
                    navigateToTimer(effect.selection)
                }
            }
        }
    }

    ScreenContent(
        state = state,
        eventHandler = viewModel::postEvent
    )
}

@Composable
private fun ScreenContent(
    state: ChessTimerSelectionState,
    eventHandler: (ChessTimerSelectionEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.padding_default)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //I want an class that has values in it, and it should also be able to select the value of it
        
    }
}