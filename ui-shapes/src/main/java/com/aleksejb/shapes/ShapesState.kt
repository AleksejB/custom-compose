package com.aleksejb.shapes

import androidx.compose.ui.graphics.Color
import com.aleksejb.domain_core.model.OutlineType


data class ShapesState(
    val numberOfSide: Int,
    val outlineType: OutlineType,
    val fillColor: Color
) {
    companion object {
        val Initial = ShapesState(
            numberOfSide = 3,
            outlineType = OutlineType.SOILD,
            fillColor = Color.Black
        )
    }
}
