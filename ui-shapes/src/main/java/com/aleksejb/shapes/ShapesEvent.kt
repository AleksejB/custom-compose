package com.aleksejb.shapes

import androidx.compose.ui.graphics.Color
import com.aleksejb.domain_core.model.OutlineType

sealed interface ShapesEvent {
    data class OnNumberOfSidesSelected(val numberOfSides: Int): ShapesEvent
    data class OnOutlineTypeSelected(val outlineType: OutlineType): ShapesEvent
    data class OnFillSelected(val fillColor: Color): ShapesEvent
}