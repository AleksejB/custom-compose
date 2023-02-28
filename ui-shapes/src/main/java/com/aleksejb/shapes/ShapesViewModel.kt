package com.aleksejb.shapes

import com.aleksejb.ui_core.viewmodel.MVIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ShapesViewModel constructor(

): MVIViewModel<ShapesState, ShapesEvent, ShapesEffect>() {

    override val _state = MutableStateFlow<ShapesState>(ShapesState.Initial)

    override fun handleEvent(event: ShapesEvent) {
        when (event) {
            is ShapesEvent.OnFillSelected -> updateState { copy(fillColor = event.fillColor) }
            is ShapesEvent.OnOutlineTypeSelected -> updateState { copy(outlineType = event.outlineType) }
            is ShapesEvent.OnNumberOfSidesSelected -> {
                if (event.numberOfSides < 3 || event.numberOfSides > 100) return
                updateState { copy(numberOfSide = event.numberOfSides) }
            }
        }
    }
}

