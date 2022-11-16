package co.navidupli.vinilos.ui.artistsScreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import co.navidupli.vinilos.R
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.model.Performer
import co.navidupli.vinilos.viewModel.ListPerformerViewModel
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ArtistsScreen(
    viewModel: ListPerformerViewModel = viewModel(),
) {
    val performers: List<Performer> = viewModel.performers.observeAsState(listOf<Performer>()).value
    ListWithHeader(performers)
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListWithHeader(performers: List<Performer>) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .testTag("listPerformers")
    ) {

        stickyHeader {

            Text(
                text = stringResource(R.string.artistas),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .heightIn(min = 56.dp)
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxSize()
                    .padding(vertical = 4.dp)
                    .testTag("titlePerformer")

            )
        }

        itemsIndexed(performers) { index,performer ->
            val date: String =
                if (performer.birthDate != null) performer.birthDate else performer.creationDate
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val parseDate: Date = format.parse(date) as Date
            val simpleDateFormat = SimpleDateFormat("yyyy")
            val dateFormatted = simpleDateFormat.format(parseDate)
            ComponentCard(
                tittle = performer.name,
                date = dateFormatted,
                subtext = "",
                imageUrl = performer.image,
                testTag = "artistItem_$index",
                onClick = { }
            )
        }
    }
}