package co.navidupli.vinilos.ui.createAlbumScreen

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import co.navidupli.vinilos.viewModel.CreateAlbumViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showSystemUi = true)
@Composable
fun CreateAlbumScreen() {
    val createAlbumViewModel = CreateAlbumViewModel()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        Space()

        AlbumName(createAlbumViewModel)
        Space()

        AlbumCover(createAlbumViewModel)
        Space()

        AlbumDateRelease(createAlbumViewModel, context)
        Space()

        AlbumDesc(createAlbumViewModel)
        Space()

        AlbumGenre(createAlbumViewModel)
        Space()

        AlbumRecordLabel(createAlbumViewModel)
        Space()

        SaveButton(createAlbumViewModel)
        Space()
    }

}

@Composable
fun Title() {
    Text(
        text = "Crear Album",
        fontSize = 20.sp
    )
}

@Composable
fun Space() {
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun AlbumName(viewModel: CreateAlbumViewModel) {
    val albumName: String by viewModel.nameAlbum.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Nombre") },
        value = albumName,
        onValueChange = { viewModel.setNameAlbum(it) }
    )
}

@Composable
fun AlbumCover(viewModel: CreateAlbumViewModel) {
    val albumCover: String by viewModel.coverAlbum.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Cover") },
        value = albumCover,
        onValueChange = { viewModel.setCoverAlbum(it) }
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AlbumDateRelease(viewModel: CreateAlbumViewModel, context: Context) {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    var listener: DatePickerDialog.OnDateSetListener? = null
    val albumDate: String by viewModel.dateReleaseAlbum.observeAsState(initial = "")
    val datePickerDialog = DatePickerDialog(context, { _, year: Int, month: Int, dayOfMonth: Int -> viewModel.setDateReleaseAlbum("$dayOfMonth/$month/$year") }, year, month, day)

    TextField(
        label = { Text(text = "Fecha de lanzamiento") },
        value = albumDate,
        enabled = false,
        modifier = Modifier
            .clickable { datePickerDialog.show() },
        onValueChange = {  }
    )
}

@Composable
fun AlbumDesc(viewModel: CreateAlbumViewModel) {
    val albumDesc: String by viewModel.descriptionAlbum.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Descripción") },
        value = albumDesc,
        onValueChange = {viewModel.setDescriptionAlbum(it) }
    )
}

@Composable
fun AlbumGenre(viewModel: CreateAlbumViewModel) {
    val options = listOf("Classical", "Salsa", "Rock", "Folk")
    val genreAlbum: String by viewModel.genreAlbum.observeAsState(initial = "")
    DropDownList(options = options, "Género", genreAlbum) {
        viewModel.setGenreAlbum(it)
    }
}

@Composable
fun AlbumRecordLabel(viewModel: CreateAlbumViewModel) {
    val options = listOf("Sony Music", "EMMY", "Discos Fuentes", "Elektra", "Fania Record")
    val albumRecord: String by viewModel.recordLabelAlbum.observeAsState(initial = "")
    DropDownList(options = options, "Sello discografíco", albumRecord) {
        viewModel.setRecordLabelAlbum(it)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownList(options: List<String>, text: String, value: String, setValue: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.padding(20.dp, 0.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = value,
                onValueChange = { },
                label = { Text(text) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            setValue(selectionOption)
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
}

@Composable
fun SaveButton(viewModel: CreateAlbumViewModel) {
    Button(
        onClick = { viewModel.saveAlbum() }
    ) {
        Icon(
            imageVector = Icons.Filled.Save,
            modifier = Modifier.size(20.dp),
            contentDescription = "drawable icons"
        )

        Text(text = "Guardar")
    }
}