package com.aleksejb.customcompose.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aleksejb.customcompose.ui.theme.CustomComposeTheme
import com.aleksejb.shapes.ShapesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomComposeTheme {
                ShapesScreen()
            }
        }
    }
}