package co.navidupli.vinilos.ui.associateTracksScreen

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import co.navidupli.vinilos.model.Album
import co.navidupli.vinilos.viewModel.AssociateTracksViewModel

@Composable
@Preview(showSystemUi = true)
fun AssociateTracksScreen() {
    val asociateTracksViewModel = AssociateTracksViewModel()
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
        AlbumSelect(viewModel = asociateTracksViewModel)
        Space(15)
        AlbumName(asociateTracksViewModel)
        Space(15)
        Duracion(asociateTracksViewModel)
        Space(15)
        SaveButton(asociateTracksViewModel, context)
    }
}


@Composable
fun Title() {
    Text(
        text = "Asociar track",
        fontSize = 30.sp,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.testTag("titleAsociateTrack")
    )
}

@Composable
fun Space(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

@Composable
fun AlbumName(viewModel: AssociateTracksViewModel) {
    val trackName: String by viewModel.name.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Name") },
        value = trackName,
        modifier = Modifier
            .testTag("textFieldTrackName")
            .fillMaxWidth(),
        onValueChange = { viewModel.setTrackName(it) }
    )
}


@Composable
fun Duracion(viewModel: AssociateTracksViewModel) {
    val duration: String by viewModel.duration.observeAsState(initial = "")
    TextField(
        label = { Text(text = "Duración") },
        value = duration,
        modifier = Modifier
            .testTag("textFieldTrackDuracion")
            .fillMaxWidth(),
        onValueChange = {
            viewModel.setDurationTrack(it)
        }
    )
}



@Composable
fun AlbumSelect(viewModel: AssociateTracksViewModel) {
    val albums: List<Album> by viewModel.albums.observeAsState(initial = listOf())

    val selectAlbum: Album? by viewModel.albumSelected.observeAsState(initial = null)
    DropDownList(options = albums, "Album", selectAlbum, "selectAlbum") {
        viewModel.setAlbumSelected(it)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownList(
    options: List<Album>,
    text: String,
    value: Album?,
    testTag: String,
    setValue: (Album) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier
                    .testTag(testTag)
                    .fillMaxWidth(),
                readOnly = true,
                value = value?.name ?: "",
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
            ) {
                options.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            setValue(selectionOption)
                            expanded = false
                        },
                        modifier = Modifier
                            .testTag("albumItem_"+index)
                    ) {
                        Text(text = selectionOption.name)
                    }
                }
            }
        }
    }
}

@Composable
fun SaveButton(viewModel: AssociateTracksViewModel, context: Context) {
    val lifeCycle = LocalLifecycleOwner.current
    val loadAssociateTrack: Boolean by viewModel.loadAssociateTrack.observeAsState(initial = true)

    Button(
        onClick = {
            if(validateFields(viewModel,context)){
                viewModel.setloadAssociateTrack(!loadAssociateTrack)
                viewModel.associateTrack()
            }
        },
        enabled = loadAssociateTrack,
        modifier = Modifier.testTag("btnSaveAsociateTractToAlbum")
    ) {
        Icon(
            imageVector = Icons.Filled.Save,
            modifier = Modifier.size(20.dp),
            contentDescription = "Guardar"
        )

        Text(text = "Guardar")
    }

    showToast(lifeCycle, viewModel, context)
}

fun validateFields(viewModel: AssociateTracksViewModel, context: Context):Boolean{
    val pattern = "^[0-6][0-9]:[0-9]{2}$".toRegex()
    if(viewModel.albumSelected.value==null){
        Toast.makeText(context, "El album es obligatorio ", Toast.LENGTH_LONG).show()
        return false
    }

    if(viewModel.name.value.toString().isEmpty()){
        Toast.makeText(context, "El Nombre es obligatorio ", Toast.LENGTH_LONG).show()
    return false
    }
    if(!pattern.matches(viewModel.duration.value.toString())){
        Toast.makeText(context, "El formato del campo duración debe ser MM:SS ", Toast.LENGTH_LONG).show()
        return false
    }
    return true

}

@Composable
fun showToast(lifeCycle: LifecycleOwner, viewModel: AssociateTracksViewModel, context: Context) {

    viewModel.statusAssociate.observe(lifeCycle, Observer { status ->
        status?.let {
            viewModel.setStatusAssociateTrackAlbum()
            if (it) {
                Toast.makeText(context, "Track asociado con éxito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al asociar el tracl al album", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    })
}