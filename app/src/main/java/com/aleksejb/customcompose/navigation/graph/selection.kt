package com.aleksejb.customcompose.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.aleksejb.customcompose.navigation.Graph
import com.aleksejb.customcompose.navigation.Screen

fun NavGraphBuilder.addSelectionGraph(
    builder: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = Graph.Selection.route,
        startDestination = Screen.Selection.createRoute(Graph.Selection)
    ) {
        builder()
    }
}