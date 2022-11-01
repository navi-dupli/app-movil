package co.navidupli.vinilos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.navidupli.vinilos.ui.ProfileScreen.ProfileScreen
import co.navidupli.vinilos.ui.albumsScreen.AlbumsScreen
import co.navidupli.vinilos.ui.artistsScreen.ArtistsScreen
import co.navidupli.vinilos.ui.associateTracksScreen.AssociateTracksScreen
import co.navidupli.vinilos.ui.collectorsScreen.CollectorsScreen
import co.navidupli.vinilos.ui.createAlbumScreen.CreateAlbumScreen

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
        composable(NavigationScreen.AlbumsScreen.route) { AlbumsScreen() }
        composable(NavigationScreen.ArtistsScreen.route) { ArtistsScreen() }
        composable(NavigationScreen.CollectorsScreen.route) { CollectorsScreen() }
        composable(NavigationScreen.CreateAlbumScreen.route) { CreateAlbumScreen() }
        composable(NavigationScreen.AssociateTracksScreen.route) { AssociateTracksScreen() }
        composable(NavigationScreen.ProfileScreen.route) {
            ProfileScreen() {
                logout()
            }
        }
    }
}