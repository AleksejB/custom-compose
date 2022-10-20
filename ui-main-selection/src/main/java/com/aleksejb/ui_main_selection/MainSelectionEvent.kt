package com.aleksejb.ui_main_selection

sealed interface MainSelectionEvent {
    object OnChessTimerClicked: MainSelectionEvent
}