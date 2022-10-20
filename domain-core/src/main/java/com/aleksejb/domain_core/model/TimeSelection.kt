package com.aleksejb.domain_core.model

sealed interface TimeSelection {
    object FiveMin: TimeSelection
    object FifteenMin: TimeSelection
    object ThirtyMin: TimeSelection
}