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
import co.navidupli.vinilos.model.Performer
import co.navidupli.vinilos.viewmodel.DetailArtistViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ArtistDetailScreen(
    viewModel: DetailArtistViewModel = viewModel(),
    navController: NavController,
    PerformerId: Int?,
    IsBand: Boolean?
) {

    if (PerformerId != null ) {
        viewModel.getPerformerDetail(PerformerId, IsBand )
    }
    val performer: Performer? = viewModel.performer.observeAsState().value

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
            imageUrl = performer?.image,
            name = performer?.name,
            year =  performer?.birthDate ?: performer?.creationDate,
            description = performer?.description,
            genre = null,
            recordLabel = null,
        )

        Spacer(modifier = Modifier.height(15.dp))

        var dateFormatted: String
        var format: SimpleDateFormat?
        var date: Date?
        var simpleDateFormat: SimpleDateFormat?

        performer?.musicians?.forEach { musician ->

            try {
                format = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.US
                )
                date = format!!.parse( musician.birthDate ?: "")
                simpleDateFormat = SimpleDateFormat("yyyy",Locale.US)
                dateFormatted = simpleDateFormat!!.format(date!!)
            }catch (ex: ParseException){
                dateFormatted=""
            }

            ComponentCard(
                tittle = musician.name,
                date = dateFormatted,
                subtext = "",
                imageUrl = musician.image,
                testTag = null,
                onClick = {

                }
            )
        }
    }
}
