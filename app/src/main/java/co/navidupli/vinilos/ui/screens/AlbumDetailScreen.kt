package co.navidupli.vinilos.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.components.DetailComponent
import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.viewmodel.DetailAlbumViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
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
        IconButton(modifier = Modifier
            .align(alignment = Alignment.Start)
            .testTag("goBack"),
            onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "")
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

        var dateFormatted: String
        var format: SimpleDateFormat?
        var date: Date?
        var simpleDateFormat: SimpleDateFormat?

        album?.performers?.forEach { performer ->

            try {
                format = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                    Locale.getDefault(Locale.Category.FORMAT)
                )
                date = format!!.parse( performer?.birthDate ?:"")
                simpleDateFormat = SimpleDateFormat("yyyy", Locale.US)
                dateFormatted = simpleDateFormat!!.format(date!!)
            }catch (ex: ParseException){
                dateFormatted=""
            }

            ComponentCard(
                tittle = performer.name,
                date = dateFormatted,
                subtext = "",
                imageUrl = performer.image,
                testTag = null,
                onClick = { }
            )
        }
    }
}
