package co.navidupli.vinilos.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import co.navidupli.vinilos.R
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.model.Performer
import co.navidupli.vinilos.navigation.NavigationScreen
import co.navidupli.vinilos.viewmodel.ListPerformerViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ArtistsScreen(
    viewModel: ListPerformerViewModel = viewModel(), navController: NavHostController
) {
    val performers: List<Performer> = viewModel.performers.observeAsState(listOf()).value
    ListWithHeader(performers, navController)
}

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListWithHeader(performers: List<Performer>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .testTag("listPerformers")
    ) {

        stickyHeader {

            Text(
                text = stringResource(R.string.artistas),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSecondary,
                maxLines = 1,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .heightIn(min = 56.dp)
                    .background(color = MaterialTheme.colors.secondary)
                    .fillMaxSize()
                    .padding(vertical = 4.dp)
                    .testTag("titlePerformer")

            )
        }

        var dateFormatted: String
        var format: SimpleDateFormat?
        var date: Date?
        var simpleDateFormat: SimpleDateFormat?
        var fecha: String
        itemsIndexed(performers) { index, performer ->
            val isBand: Boolean = performer.birthDate == null
            fecha = performer.birthDate ?: performer.creationDate
            format = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US
            )
            date = format!!.parse(fecha)
            simpleDateFormat = SimpleDateFormat("yyyy", Locale.US)
            dateFormatted = simpleDateFormat!!.format(date!!)

            ComponentCard(tittle = performer.name,
                date = dateFormatted,
                subtext = "",
                imageUrl = performer.image,
                testTag = "artistItem_$index",
                onClick = {
                    navController.navigate(NavigationScreen.ArtistDetailScreen.route + "/${performer.id}/${isBand}")
                })
        }
    }
}