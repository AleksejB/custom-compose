package com.aleksejb.domain_core.model

sealed class TimeSelection(val minutes: Int) {
    object FiveMin: TimeSelection(5)
    object FifteenMin: TimeSelection(15)
    object ThirtyMin: TimeSelection(30)
}