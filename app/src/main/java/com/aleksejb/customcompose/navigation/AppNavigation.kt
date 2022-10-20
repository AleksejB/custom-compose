package com.aleksejb.customcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.aleksejb.customcompose.navigation.graph.addSelectionGraph
import com.aleksejb.customcompose.navigation.screen.addMainSelectionScreen


sealed class Graph(val route: String) {
    object Selection: Graph("selection")
}

sealed class Screen(val route: String) {
    fun createRoute(graph: Graph): String {
        return "${graph.route}/$route"
    }

    object Selection: Screen("selection")
}

@Composable
internal fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Graph.Selection.route
    ) {
        addSelectionGraph {
            addMainSelectionScreen(navController)
        }
    }
}