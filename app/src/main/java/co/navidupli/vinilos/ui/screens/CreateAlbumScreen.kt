package co.navidupli.vinilos.ui.screens

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import co.navidupli.vinilos.viewmodel.CreateAlbumViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

@Preview(showSystemUi = true)
@Composable
fun CreateAlbumScreen() {
    val createAlbumViewModel = CreateAlbumViewModel()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = "Crear Álbum", testTag = "titleCreateAlbum")
        Space(15)

        AlbumName(createAlbumViewModel)
        Space(15)

        AlbumCover(createAlbumViewModel)
        Space(15)

        AlbumDateRelease(createAlbumViewModel, context)
        Space(15)

        AlbumDesc(createAlbumViewModel)
        Space(15)

        AlbumGenre(createAlbumViewModel)
        Space(15)

        AlbumRecordLabel(createAlbumViewModel)
        Space(15)

        SaveButton(createAlbumViewModel, context)
        Space(50)
    }

}


@Composable
private fun AlbumName(viewModel: CreateAlbumViewModel) {
    val albumName: String by viewModel.nameAlbum.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Nombre") },
        value = albumName,
        modifier = Modifier.testTag("textFieldAlbumName")
        .fillMaxWidth(),
        onValueChange = { viewModel.setNameAlbum(it) }
    )
}

@Composable
private fun AlbumCover(viewModel: CreateAlbumViewModel) {
    val albumCover: String by viewModel.coverAlbum.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Cover") },
        value = albumCover,
        modifier = Modifier.testTag("textFieldAlbumCover")
        .fillMaxWidth(),
        onValueChange = { viewModel.setCoverAlbum(it) }
    )
}

@Composable
private fun AlbumDateRelease(viewModel: CreateAlbumViewModel, context: Context) {
    val c = getInstance()
    var year1 = c.get(YEAR)
    var month1 = c.get(MONTH)
    var day1 = c.get(DAY_OF_MONTH)

    val albumDate: String by viewModel.dateReleaseAlbum.observeAsState(initial = "")
    if (albumDate != "") {
        val sdf =  SimpleDateFormat("MM/dd/yyyy",Locale.US)
        val date: Date = sdf.parse(albumDate) as Date
        val cal = getInstance()
        cal.time = date
        year1 = cal.get(YEAR)
        month1 = cal.get(MONTH)
        day1 = cal.get(DAY_OF_MONTH)
    } else {
        viewModel.setDateReleaseAlbum("${month1 + 1}/$day1/$year1")
    }

    val datePickerDialog = DatePickerDialog(
        context, { _,
                   year: Int, month: Int, dayOfMonth: Int ->
            viewModel.setDateReleaseAlbum("${month + 1}/$dayOfMonth/$year")
        },
        year1, month1, day1
    )

    TextField(
        label = { Text(text = "Fecha de lanzamiento") },
        value = albumDate,
        enabled = false,
        modifier = Modifier
            .clickable { datePickerDialog.show() }
            .testTag("textFieldAlbumReleaseDate")
            .fillMaxWidth(),
        onValueChange = { viewModel.setDateReleaseAlbum(it) }
    )
}

@Composable
private fun AlbumDesc(viewModel: CreateAlbumViewModel) {
    val albumDesc: String by viewModel.descriptionAlbum.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Descripción") },
        value = albumDesc,
        modifier = Modifier.testTag("textFieldAlbumDesc")
        .fillMaxWidth(),
        onValueChange = {viewModel.setDescriptionAlbum(it) }
    )
}

@Composable
private fun AlbumGenre(viewModel: CreateAlbumViewModel) {
    val options = listOf("Classical", "Salsa", "Rock", "Folk")
    val genreAlbum: String by viewModel.genreAlbum.observeAsState(initial = "")
    DropDownList(options = options, "Género", genreAlbum, "selectAlbumGenre") {
        viewModel.setGenreAlbum(it)
    }
}

@Composable
private fun AlbumRecordLabel(viewModel: CreateAlbumViewModel) {
    val options = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
    val albumRecord: String by viewModel.recordLabelAlbum.observeAsState(initial = "")
    DropDownList(options = options, "Sello discografíco", albumRecord, "selectAlbumRecordLabel") {
        viewModel.setRecordLabelAlbum(it)
    }
}

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DropDownList(options: List<String>, text: String, value: String, testTag: String, setValue: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.testTag(testTag)
                                .fillMaxWidth(),
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
                },
                modifier = Modifier.fillMaxWidth(),
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
private fun SaveButton(viewModel: CreateAlbumViewModel, context: Context) {
    val lifeCycle = LocalLifecycleOwner.current
    val loadCreate: Boolean by viewModel.loadCreateAlbum.observeAsState(initial = true)

    Button(
        onClick = {
            viewModel.setLoadCreate(!loadCreate)
            viewModel.saveAlbum()
        },
        enabled = loadCreate,
        modifier = Modifier.testTag("btnCreateAlbum")
    ) {
        Icon(
            imageVector = Icons.Filled.Save,
            modifier = Modifier.size(20.dp),
            contentDescription = "drawable icons"
        )

        Text(text = "Guardar")
    }

    ShowToast(lifeCycle, viewModel, context)
}


@Composable
private fun ShowToast(lifeCycle: LifecycleOwner, viewModel: CreateAlbumViewModel, context: Context) {

    viewModel.statusCreateAlbum.observe(lifeCycle) { status ->
        status?.let {
            viewModel.setStatusCreateAlbum()
            if (it) {
                Toast.makeText(context, "Album creado con éxito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al crear el album", Toast.LENGTH_SHORT).show()
            }
        }
    }
}