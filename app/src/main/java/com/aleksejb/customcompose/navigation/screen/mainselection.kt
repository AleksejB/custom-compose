package com.aleksejb.customcompose.navigation.screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aleksejb.customcompose.navigation.Graph
import com.aleksejb.customcompose.navigation.Screen

fun NavGraphBuilder.addMainSelectionScreen(navController: NavController) {
    composable(
        route = Screen.Selection.createRoute(Graph.Selection)
    ) {
        //the screen it self
    }
}