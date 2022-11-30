package co.navidupli.vinilos.bottombar

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import co.navidupli.vinilos.navigation.NavigationScreen

@Composable
fun BottomBar(navController: NavController, type: Int?) {
    val items: List<NavigationScreen> = if (type == 1) {
        listOf(
            NavigationScreen.CreateAlbumScreen,
            NavigationScreen.AssociateTracksScreen,
            NavigationScreen.ProfileScreen
        )
    }else{
        listOf(
            NavigationScreen.AlbumsScreen,
            NavigationScreen.ArtistsScreen,
            NavigationScreen.CollectorsScreen,
            NavigationScreen.ProfileScreen
        )
    }

    BottomNavigation(
        elevation = 5.dp,
        modifier = Modifier.background(MaterialTheme.colors.primary)
    ) {
        val navBackStackEntry: NavBackStackEntry? = navController.currentBackStackEntryAsState().value
        val currentRoute = navBackStackEntry?.destination?.route
        items.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(
                        text = it.title
                    )
                },
                selected = currentRoute == it.route,
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.primary.copy(alpha = 0.7f),
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.testTag("btn_${it.route}").background(MaterialTheme.colors.primaryVariant)
                )
        }

    }
}