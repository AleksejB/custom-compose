package com.aleksejb.shapes

import androidx.compose.ui.graphics.Color


data class ShapesState(
    val numberOfSide: Int,
    val outlineType: OutlineType,
    val fillColor: Color
) {
    companion object {
        val Initial = ShapesState(
            numberOfSide = 3,
            outlineType = OutlineType.SOILD,
            fillColor = Color.White
        )
    }
}

enum class OutlineType {
    SOILD,
    DASHED
}