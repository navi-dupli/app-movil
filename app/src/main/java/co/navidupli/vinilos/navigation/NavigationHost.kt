package co.navidupli.vinilos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import co.navidupli.vinilos.ui.screens.*

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
        composable(NavigationScreen.ArtistsScreen.route) { ArtistsScreen(navController = navController) }
        composable(NavigationScreen.CollectorsScreen.route) { CollectorsScreen(navController = navController) }
        composable(NavigationScreen.CreateAlbumScreen.route) { CreateAlbumScreen() }
        composable(NavigationScreen.AssociateTracksScreen.route) { AssociateTracksScreen() }
        composable(
            route = NavigationScreen.AlbumDetailScreen.route + "/{album_id}",
            arguments = listOf(
                navArgument("album_id") {
                    type = NavType.IntType
                }),
        ) {
            AlbumDetailScreen(
                albumId = it.arguments?.getInt("album_id"),
                navController = navController
            )
        }
        composable(
            route = NavigationScreen.ArtistDetailScreen.route + "/{performer_id}/{is_band}",
            arguments = listOf(
                navArgument("performer_id") {
                    type = NavType.IntType
                },
                navArgument("is_band") {
                    type = NavType.BoolType
                }
            ),
        ) {
            ArtistDetailScreen(
                performerId = it.arguments?.getInt("performer_id"),
                isBand = it.arguments?.getBoolean("is_band"),
                navController = navController
            )
        }
        composable(NavigationScreen.ProfileScreen.route) {
            ProfileScreen {
                logout()
            }
        }
        composable(
            route = NavigationScreen.CollectorDetailScreen.route + "/{collector_id}",
            arguments = listOf(
                navArgument("collector_id") {
                    type = NavType.IntType
                }),
        ) {
            CollectorDetailScreen(
                collectorId = it.arguments?.getInt("collector_id"),
                navController = navController
            )
        }
    }
}