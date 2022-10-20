package com.aleksejb.ui_main_selection

sealed interface MainSelectionEffect {
    object NavigateToChessTimer: MainSelectionEffect
}