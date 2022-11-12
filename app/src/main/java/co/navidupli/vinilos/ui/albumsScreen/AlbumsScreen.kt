package co.navidupli.vinilos.ui.albumsScreen
import java.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.viewModel.ListAlbumsViewModel
import java.util.*
import androidx.lifecycle.viewmodel.compose.viewModel
import co.navidupli.vinilos.model.Album
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import co.navidupli.vinilos.R
import co.navidupli.vinilos.navigation.NavigationScreen


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AlbumsScreen(
    viewModel: ListAlbumsViewModel = viewModel(),
    navController: NavHostController
) {
    val albums: List<Album> = viewModel.albums.observeAsState(listOf<Album>()).value
    ListWithHeader(albums, navController)
}


@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListWithHeader(albums: List<Album>, navController: NavHostController) {
    LazyColumn(
        modifier=Modifier.padding(bottom = 60.dp).testTag("listAlbums")
    ) {

        stickyHeader {

            Text(
                text =  stringResource(R.string.albumes),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .heightIn(min = 56.dp)
                    .background(color = MaterialTheme.colors.background )
                    .fillMaxSize()
                    .padding( vertical = 4.dp)
                    .testTag("titleAlbum")

            )
        }

        items(albums) { album ->
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val date: Date = format.parse(album.releaseDate)
            val simpleDateFormat =SimpleDateFormat("yyyy")
            ComponentCard(
                tittle = album.name,
                date = simpleDateFormat.format(date),
                subtext = album.genre,
                imageUrl = album.cover,
                onClick = {
                    navController.navigate(NavigationScreen.AlbumDetailScreen.route+"/${album.id}")
                }
            )
        }
    }
}






