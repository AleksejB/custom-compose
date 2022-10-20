package com.aleksejb.ui_main_selection

import com.aleksejb.ui_core.viewmodel.MVIViewModel

class MainSelectionViewModel constructor(

): MVIViewModel<MainSelectionEffect, MainSelectionEvent, MainSelectionState>() {





    override fun handleEvent(event: MainSelectionEvent) {
        TODO("Not yet implemented")
    }

    override fun createInitialState(): MainSelectionState {
        return MainSelectionState.Initial
    }
}