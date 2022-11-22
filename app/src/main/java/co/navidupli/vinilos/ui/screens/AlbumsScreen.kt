@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package co.navidupli.vinilos.ui.screens

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import co.navidupli.vinilos.R
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.navigation.NavigationScreen
import co.navidupli.vinilos.viewmodel.ListAlbumsViewModel
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun AlbumsScreen(
    viewModel: ListAlbumsViewModel = viewModel(),
    navController: NavHostController
) {
    val albums: List<Album> = viewModel.albums.observeAsState(listOf()).value
    ListWithHeader(albums, navController)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListWithHeader(albums: List<Album>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .testTag("listAlbums")
    ) {

        stickyHeader {

            Text(
                text = stringResource(R.string.albumes),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .heightIn(min = 56.dp)
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxSize()
                    .padding(vertical = 4.dp)
                    .testTag("titleAlbum")

            )
        }
        var dateFormatted: String
        var format: SimpleDateFormat?
        var date: Date?
        var simpleDateFormat: SimpleDateFormat?

        items(albums) { album ->
            format = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.US
            )
            date = format!!.parse("" + album.releaseDate)
            simpleDateFormat = SimpleDateFormat("yyyy",Locale.US)
            dateFormatted = simpleDateFormat!!.format(date!!)

            ComponentCard(
                tittle = album.name,
                date = dateFormatted,
                subtext = album.genre,
                imageUrl = album.cover,
                testTag = "itemCard",
                onClick = {
                    navController.navigate(NavigationScreen.AlbumDetailScreen.route + "/${album.id}")
                }
            )
        }
    }
}






