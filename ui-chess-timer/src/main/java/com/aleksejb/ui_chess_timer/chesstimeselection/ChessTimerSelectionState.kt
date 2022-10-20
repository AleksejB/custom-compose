package com.aleksejb.ui_chess_timer.chesstimeselection

import com.aleksejb.domain_core.model.TimeSelection

data class ChessTimerSelectionState(
    val timeSelection: TimeSelection
) {
    companion object {
        val Default = ChessTimerSelectionState(
            timeSelection = TimeSelection.ThirtyMin
        )
    }
}

