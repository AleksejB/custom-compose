package com.aleksejb.ui_chess_timer.chesstimeselection

import com.aleksejb.domain_core.model.TimeSelection

sealed interface ChessTimerSelectionEvent {
    data class OnSelectionChanged(val selection: TimeSelection): ChessTimerSelectionEvent
    object OnNextClicked: ChessTimerSelectionEvent
}