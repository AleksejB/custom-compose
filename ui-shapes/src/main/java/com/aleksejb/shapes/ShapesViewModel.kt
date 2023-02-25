package com.aleksejb.shapes

import com.aleksejb.ui_core.viewmodel.MVIViewModel

class ShapesViewModel constructor(

): MVIViewModel<ShapesEffect, ShapesEvent, ShapesState>() {

    override fun handleEvent(event: ShapesEvent) {
        when (event) {
            else -> {}
        }
    }

    override fun createInitialState(): ShapesState {
        return ShapesState.Initial
    }
}