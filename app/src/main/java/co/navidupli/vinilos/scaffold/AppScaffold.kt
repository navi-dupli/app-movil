package co.navidupli.vinilos.scaffold

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import co.navidupli.vinilos.bottombar.BottomBar
import co.navidupli.vinilos.navigation.NavigationHost
import co.navidupli.vinilos.navigation.NavigationScreen

@Composable
fun AppScaffold(
    navController: NavController,
    type: Int?,
    logout: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, type)
        },
        scaffoldState = scaffoldState,

        ) {
        val route: String =
            if (type == 0) NavigationScreen.AlbumsScreen.route else NavigationScreen.CreateAlbumScreen.route

        NavigationHost(navController = navController, route) {
            logout()

        }
    }

}