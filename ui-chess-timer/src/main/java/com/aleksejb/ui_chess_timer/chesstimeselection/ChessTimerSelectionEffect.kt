package com.aleksejb.ui_chess_timer.chesstimeselection

import com.aleksejb.domain_core.model.TimeSelection

sealed interface ChessTimerSelectionEffect {
    data class NavigateToTimer(val selection: TimeSelection): ChessTimerSelectionEffect
}