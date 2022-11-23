package co.navidupli.vinilos.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import co.navidupli.vinilos.components.ComponentCard
import co.navidupli.vinilos.model.Collector
import co.navidupli.vinilos.ui.theme.LightPink
import co.navidupli.vinilos.viewmodel.DetailCollectorViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CollectorDetailScreen(
    viewModel: DetailCollectorViewModel = viewModel(),
    navController: NavController,
    collectorId: Int?
) {

    if (collectorId != null) {
        viewModel.getCollectorDetail(collectorId)
    }
    val collector: Collector? = viewModel.collector.observeAsState().value

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

        collector?.name?.let {
            Text(
                text = it, fontSize = 28.sp, modifier = Modifier
                    .testTag("nameDetailCollector")
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        collector?.telephone?.let {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Surface(
                    color = LightPink, shape = MaterialTheme.shapes.small,
                ) {

                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.testTag("telephoneDetailCollector")
                    )
                }
            }
        }

        collector?.email?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp, 0.dp)
                    .testTag("emailDetailCollector")
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Artistas favoritos", fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        var dateFormatted: String
        var format: SimpleDateFormat?
        var date: Date?
        var simpleDateFormat: SimpleDateFormat?

        collector?.favoritePerformers?.forEach { performer ->

            try {
                format = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US
                )
                date = format!!.parse( performer.birthDate ?: performer.creationDate)
                simpleDateFormat = SimpleDateFormat("yyyy", Locale.US)
                dateFormatted = simpleDateFormat!!.format(date!!)
            }catch (ex: ParseException){
                dateFormatted=""
            }

            ComponentCard(
                tittle = performer.name,
                date = dateFormatted,
                subtext = "",
                imageUrl = performer.image,
                testTag = null,
                onClick = { }
            )
        }
    }
}
