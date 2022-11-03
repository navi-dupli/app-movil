package co.navidupli.vinilos.ui.albumsScreen

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.model.AlbumCreated
import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AlbumsScreen(
    modifier: Modifier = Modifier,
) {
    var albums = listOf<AlbumCreated>(
        AlbumCreated(
            cover = "https://upload.wikimedia.org/wikipedia/en/4/4d/Queen_A_Night_At_The_Opera.png",
            description = "Es el cuarto álbum de estudio de la banda británica de rock Queen, publicado originalmente en 1975",
            genre = "Rock",
            id = 102,
            name = "A Night at the Opera",
            recordLabel = "EMI",
            releaseDate = "1975-11-21T00:00:00.000Z"
        ),
        AlbumCreated(
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            description = "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984",
            genre = "Salsa",
            id = 101,
            name = "Buscando América",
            recordLabel = "Elektra",
            releaseDate = "1984-08-01T00:00:00.000Z"
        )
    )
    ListWithHeader(albums)

}


@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListWithHeader(albums: List<AlbumCreated>) {
    LazyColumn {

        stickyHeader {

            Text(
                text = "Álbumes",
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

            )
        }

        items(albums) { album ->
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val date: Date = format.parse(album.releaseDate)
            val simpleDateFormat =SimpleDateFormat("YYYY")
            ComponentCard(
                tittle = album.name,
                date = simpleDateFormat.format(date),
                subtext = album.genre,
                imageUrl = album.cover
            )
        }
    }
}






