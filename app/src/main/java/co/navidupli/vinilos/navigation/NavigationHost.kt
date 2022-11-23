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
import co.navidupli.vinilos.model.Performer
import co.navidupli.vinilos.ui.screens.*

@RequiresApi(Build.VERSION_CODES.N)
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
        ) {ArtistDetailScreen(
                PerformerId = it.arguments?.getInt("performer_id"),
                IsBand = it.arguments?.getBoolean("is_band"),
                navController = navController
            )
        }
        composable(NavigationScreen.ProfileScreen.route) {
            ProfileScreen {
                logout()
            }
        }
    }
}