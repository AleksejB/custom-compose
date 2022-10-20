package com.aleksejb.ui_main_selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aleksejb.ui_core.R
import kotlinx.coroutines.flow.collect

@Composable
internal fun MainSelectionScreen(
    navigateToChessTimer: () -> Unit
) {
    val viewModel = hiltViewModel<MainSelectionViewModel>()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effects) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is MainSelectionEffect.NavigateToChessTimer -> {
                    navigateToChessTimer()
                }
            }
        }
    }

    MainSelectionScreenContent(
        state = state,
        eventHandler = viewModel::postEvent
    )
}

@Composable
private fun MainSelectionScreenContent(
    state: State<MainSelectionState>,
    eventHandler: (MainSelectionEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_default))
            .fillMaxSize()
    ) {
        Button(
            onClick = { eventHandler(MainSelectionEvent.OnChessTimerClicked) }
        ) {
            Text(
                text = stringResource(R.string.chess_timer)
            )
        }
    }
}