package co.navidupli.vinilos.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationScreen(val route: String, val title: String, val icon: ImageVector) {
    object AlbumsScreen : NavigationScreen(
        route = "albums_screen",
        title = "Albumes",
        icon = Icons.Filled.LibraryMusic
    )

    object ArtistsScreen :
        NavigationScreen(
            route = "artists_screen",
            title = "Artistas",
            icon = Icons.Filled.Groups
        )

    object CollectorsScreen :
        NavigationScreen(
            route = "collectors_screen",
            title = "Coleccionistas",
            icon = Icons.Filled.Contacts
        )

    object CreateAlbumScreen :
        NavigationScreen(
            route = "create_albums_screen",
            title = "Albumes",
            icon = Icons.Filled.LibraryMusic
        )

    object AssociateTracksScreen :
        NavigationScreen(
            route = "associate_tracks_screen",
            title = "Tracks",
            icon = Icons.Filled.MusicVideo
        )

    object ProfileScreen :
        NavigationScreen(
            route = "profile_screen",
            title = "Perfil",
            icon = Icons.Filled.Settings
        )

    object AlbumDetailScreen :
        NavigationScreen(
            route = "album_detail_screen",
            title = "Detalle",
            icon = Icons.Filled.Settings
        )

    object ArtistDetailScreen :
        NavigationScreen(
            route = "artist_detail_screen",
            title = "Detalle Artista",
            icon = Icons.Filled.Groups
        ) {

    }
}
