package co.navidupli.vinilos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import co.navidupli.vinilos.Screen
import co.navidupli.vinilos.scaffold.AppScaffold
import co.navidupli.vinilos.ui.theme.RootScreen

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    val navBarNavController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.RootScren.route) {
        composable(
            route = Screen.RootScren.route,
            content = {
                RootScreen(navController)
            })
        composable(
            route = Screen.AppScaffold.route+ "/{type}",
            arguments = listOf(navArgument("type"){
                type = NavType.IntType
            }),
            content = {
                AppScaffold(navController = navBarNavController, it.arguments?.getInt("type")) {
                    navController.navigate(Screen.RootScren.route)
                }
            }
        )
    }
}