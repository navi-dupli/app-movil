package co.navidupli.vinilos.ui.albumDetailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.navidupli.vinilos.viewModel.DetailAlbumViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.components.DetailComponent
import co.navidupli.vinilos.model.Album
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AlbumDetailScreen(
    viewModel: DetailAlbumViewModel = viewModel(),
    navController: NavController,
    albumId: Int?
) {
    if (albumId != null) {
        viewModel.getAlbumDetail(albumId)
    }
    val album: Album? = viewModel.album.observeAsState().value
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton( modifier = Modifier.align(alignment = Alignment.Start),
            onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Localized description")
        }
        DetailComponent(
            imageUrl = album?.cover,
            name = album?.name,
            year = album?.releaseDate,
            genre = album?.genre,
            recordLabel = album?.recordLabel,
            description = album?.description
        )

        Spacer(modifier = Modifier.height(15.dp))

        album?.performers?.forEach { performer ->
            var dateFormatted = ""
            if (performer.birthDate != null) {
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                val date: Date = format.parse(performer.birthDate)
                val simpleDateFormat =SimpleDateFormat("yyyy")
                dateFormatted = simpleDateFormat.format(date)
            }
            ComponentCard(
                tittle = performer.name,
                date = dateFormatted,
                subtext = "",
                imageUrl = performer.image,
                onClick = { }
            )
        }
    }
}
