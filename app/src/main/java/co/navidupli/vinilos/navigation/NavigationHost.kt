package co.navidupli.vinilos.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import co.navidupli.vinilos.ui.screens.ProfileScreen
import co.navidupli.vinilos.ui.screens.AlbumDetailScreen
import co.navidupli.vinilos.ui.screens.AlbumsScreen
import co.navidupli.vinilos.ui.screens.ArtistsScreen
import co.navidupli.vinilos.ui.screens.AssociateTracksScreen
import co.navidupli.vinilos.ui.screens.CollectorsScreen
import co.navidupli.vinilos.ui.screens.CreateAlbumScreen

@Composable
fun NavigationHost(
    navController: NavController,
    route: String,
    logout: () -> Unit
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = route
    ) {
        composable(NavigationScreen.AlbumsScreen.route) { AlbumsScreen(navController = navController) }
        composable(NavigationScreen.ArtistsScreen.route) { ArtistsScreen() }
        composable(NavigationScreen.CollectorsScreen.route) { CollectorsScreen() }
        composable(NavigationScreen.CreateAlbumScreen.route) { CreateAlbumScreen() }
        composable(NavigationScreen.AssociateTracksScreen.route) { AssociateTracksScreen() }
        composable(
            route = NavigationScreen.AlbumDetailScreen.route + "/{album_id}",
            arguments = listOf(
                navArgument("album_id") {
                    type = NavType.IntType
                }),
        ) { AlbumDetailScreen(albumId = it.arguments?.getInt("album_id"), navController = navController) }
        composable(NavigationScreen.ProfileScreen.route) {
            ProfileScreen {
                logout()
            }
        }
    }
}