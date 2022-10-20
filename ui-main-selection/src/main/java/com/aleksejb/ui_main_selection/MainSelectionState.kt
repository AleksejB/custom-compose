package com.aleksejb.ui_main_selection

data class MainSelectionState(
    val a: String
) {
    companion object {
        val Initial = MainSelectionState(
            a = "a"
        )
    }
}