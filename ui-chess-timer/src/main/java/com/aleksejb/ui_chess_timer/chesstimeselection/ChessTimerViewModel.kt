package com.aleksejb.ui_chess_timer.chesstimeselection

import com.aleksejb.ui_core.viewmodel.MVIViewModel
import javax.inject.Inject

class ChessTimerViewModel @Inject constructor(

): MVIViewModel<ChessTimerSelectionEffect, ChessTimerSelectionEvent, ChessTimerSelectionState>() {

    override fun createInitialState(): ChessTimerSelectionState {
        return ChessTimerSelectionState.Default
    }

    override fun handleEvent(event: ChessTimerSelectionEvent) {
        when (event) {
            is ChessTimerSelectionEvent.OnSelectionChanged -> {

            }
            is ChessTimerSelectionEvent.OnNextClicked -> {

            }
        }
    }

}