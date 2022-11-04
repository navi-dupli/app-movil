package co.navidupli.vinilos.ui.createAlbumScreen

import android.app.DatePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import co.navidupli.vinilos.viewModel.CreateAlbumViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
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
        Title()
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
fun Title() {
    Text(
        text = "Crear Album",
        fontSize = 20.sp,
        modifier = Modifier.testTag("titleCreateAlbum")
    )
}

@Composable
fun Space(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

@Composable
fun AlbumName(viewModel: CreateAlbumViewModel) {
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
fun AlbumCover(viewModel: CreateAlbumViewModel) {
    val albumCover: String by viewModel.coverAlbum.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Cover") },
        value = albumCover,
        modifier = Modifier.testTag("textFieldAlbumCover")
        .fillMaxWidth(),
        onValueChange = { viewModel.setCoverAlbum(it) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlbumDateRelease(viewModel: CreateAlbumViewModel, context: Context) {
    val c = Calendar.getInstance()
    var year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    var day = c.get(Calendar.DAY_OF_MONTH)

    val albumDate: String by viewModel.dateReleaseAlbum.observeAsState(initial = "")
    if (albumDate != "") {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val date: Date = sdf.parse(albumDate)
        val cal = Calendar.getInstance()
        cal.time = date
        year = cal.get(Calendar.YEAR)
        month = cal.get(Calendar.MONTH)
        day = cal.get(Calendar.DAY_OF_MONTH)
    }

    val datePickerDialog = DatePickerDialog(
        context, { _,
                   year: Int, month: Int, dayOfMonth: Int ->
            viewModel.setDateReleaseAlbum("${month + 1}/$dayOfMonth/$year")
        },
        year, month, day
    )

    TextField(
        label = { Text(text = "Fecha de lanzamiento") },
        value = albumDate,
        enabled = false,
        modifier = Modifier
            .clickable { datePickerDialog.show() }
            .fillMaxWidth()
            .testTag("textFieldAlbumReleaseDate"),
        onValueChange = {  }
    )
}

@Composable
fun AlbumDesc(viewModel: CreateAlbumViewModel) {
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
fun AlbumGenre(viewModel: CreateAlbumViewModel) {
    val options = listOf("Classical", "Salsa", "Rock", "Folk")
    val genreAlbum: String by viewModel.genreAlbum.observeAsState(initial = "")
    DropDownList(options = options, "Género", genreAlbum, "selectAlbumGenre") {
        viewModel.setGenreAlbum(it)
    }
}

@Composable
fun AlbumRecordLabel(viewModel: CreateAlbumViewModel) {
    val options = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
    val albumRecord: String by viewModel.recordLabelAlbum.observeAsState(initial = "")
    DropDownList(options = options, "Sello discografíco", albumRecord, "selectAlbumRecordLabel") {
        viewModel.setRecordLabelAlbum(it)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownList(options: List<String>, text: String, value: String, testTag: String, setValue: (String) -> Unit) {
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
                readOnly = true,
                value = value,
                onValueChange = { },
                label = { Text(text) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.fillMaxWidth(),
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
fun SaveButton(viewModel: CreateAlbumViewModel, context: Context) {
    val lifeCycle = LocalLifecycleOwner.current
    val loadCreate: Boolean by viewModel.loadCreateAlbum.observeAsState(initial = true)

    Button(
        onClick = {
            viewModel.setLoadCreate(!loadCreate)
            viewModel.saveAlbum()
        },
        enabled = loadCreate
    ) {
        Icon(
            imageVector = Icons.Filled.Save,
            modifier = Modifier.size(20.dp),
            contentDescription = "drawable icons"
        )

        Text(text = "Guardar")
    }

    showToast(lifeCycle, viewModel, context)
}


@Composable
fun showToast(lifeCycle: LifecycleOwner, viewModel: CreateAlbumViewModel, context: Context) {

    viewModel.statusCreateAlbum.observe(lifeCycle, Observer { status ->
        status?.let {
            viewModel.setStatusCreateAlbum()
            if (it) {
                Toast.makeText(context, "Album creado con éxito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al crear el album", Toast.LENGTH_SHORT).show()
            }
        }
    })
}