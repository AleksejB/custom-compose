package com.aleksejb.customcompose.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aleksejb.customcompose.navigation.AppNavigation
import com.aleksejb.customcompose.ui.theme.CustomComposeTheme

@Composable
internal fun MainContent() {
    CustomComposeTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AppNavigation()
        }
    }
}