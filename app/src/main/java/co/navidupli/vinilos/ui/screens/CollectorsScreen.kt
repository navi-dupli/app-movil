package co.navidupli.vinilos.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import co.navidupli.vinilos.R
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.model.Collector
import co.navidupli.vinilos.viewmodel.ListCollectorsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.*



@Composable
fun CollectorsScreen(
    viewModel: ListCollectorsViewModel = viewModel()
) {
    val collectors: List<Collector> = viewModel.collectors.observeAsState(listOf()).value
    ListWithHeader(collectors)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListWithHeader(collectors: List<Collector>) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 60.dp)
            .testTag("listCollectors")
    ) {

        stickyHeader {

            Text(
                text = stringResource(R.string.albumes),
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
                    .testTag("titleCollectors")

            )
        }

        items(collectors) { coleccionista ->

            ComponentCard(
                tittle = coleccionista.name,
                date = coleccionista.telephone,
                subtext = coleccionista.email,
                imageUrl = null,
                testTag = "itemCard",
                onClick = {}
            )
        }
    }
}






