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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showSystemUi = true)
@Composable
fun CreateAlbumScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        Space()

        ALbumName()
        Space()

        AlbumCover()
        Space()

        AlbumDateRelease(context)
        Space()

        AlbumDesc()
        Space()

        AlbumGenre()
        Space()

        AlbumRecordLabel()
        Space()

        SaveButton()
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
fun ALbumName() {
    val albumName = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        label = { Text(text = "Nombre") },
        value = albumName.value,
        onValueChange = { albumName.value = it }
    )
}

@Composable
fun AlbumCover() {
    val albumCover = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        label = { Text(text = "Cover") },
        value = albumCover.value,
        onValueChange = { albumCover.value = it }
    )
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AlbumDateRelease(context: Context) {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    var listener: DatePickerDialog.OnDateSetListener? = null
    val albumDate = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(context, { _, year: Int, month: Int, dayOfMonth: Int -> albumDate.value = "$dayOfMonth/$month/$year" }, year, month, day)

    TextField(
        label = { Text(text = "Fecha de lanzamiento") },
        value = albumDate.value,
        enabled = false,
        modifier = Modifier
            .clickable { datePickerDialog.show() },
        onValueChange = { albumDate.value = it }
    )
}

@Composable
fun AlbumDesc() {
    val albumDesc = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        label = { Text(text = "Descripción") },
        value = albumDesc.value,
        onValueChange = { albumDesc.value = it }
    )
}

@Composable
fun AlbumGenre() {
    val options = listOf("Classical", "Salsa", "Rock", "Folk")
    DropDownList(options = options, "Género")
}

@Composable
fun AlbumRecordLabel() {
    val options = listOf("Sony Music", "EMMY", "Discos Fuentes", "Elektra", "Fania Record")
    DropDownList(options = options, "Sello discografíco")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownList(options: List<String>, text: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

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
                value = selectedOptionText,
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
                            selectedOptionText = selectionOption
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
fun SaveButton() {
    Button(
        onClick = { /*TODO*/ }
    ) {
        Icon(
            imageVector = Icons.Filled.Save,
            modifier = Modifier.size(20.dp),
            contentDescription = "drawable icons"
        )

        Text(text = "Guardar")
    }
}