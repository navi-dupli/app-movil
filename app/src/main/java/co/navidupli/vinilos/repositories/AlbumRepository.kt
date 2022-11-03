package co.navidupli.vinilos.repositories

import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.model.AlbumCreated

class AlbumRepository {
    companion object {
        var albums: List<Album> = listOf()

        suspend fun getAlbums(): List<Album>{
        albums=  listOf<Album>(
            Album(
                cover = "https://upload.wikimedia.org/wikipedia/en/4/4d/Queen_A_Night_At_The_Opera.png",
                description = "Es el cuarto álbum de estudio de la banda británica de rock Queen, publicado originalmente en 1975",
                genre = "Rock",
                id = 102,
                name = "A Night at the Opera",
                recordLabel = "EMI",
                releaseDate = "1975-11-21T00:00:00.000Z",
                comments = null,
                tracks = null,
                performers = null
            ),
            Album(
                cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
                description = "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984",
                genre = "Salsa",
                id = 101,
                name = "Buscando América",
                recordLabel = "Elektra",
                releaseDate = "1984-08-01T00:00:00.000Z",
                comments = null,
                tracks = null,
                performers = null
            )
        )
        return albums
    }
    }
}