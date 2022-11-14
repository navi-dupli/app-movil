package co.navidupli.vinilos.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.Text
import androidx.compose.ui.unit.sp
import co.navidupli.vinilos.ui.theme.LightOcean
import co.navidupli.vinilos.ui.theme.LightPink

@Composable
fun DetailComponent(
    imageUrl: String?,
    name: String?,
    year: String?,
    genre: String?,
    recordLabel: String?,
    description: String?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .testTag("detailComponent")
    ) {
        imageUrl?.let {
            Image(
                imageUrl = it,
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
                    .testTag("imageDetail")

            )
        }

        name?.let {
            Text(text = it, fontSize = 28.sp, modifier = Modifier
                .testTag("nameDetail"))
        }

        year?.let {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val date: Date = format.parse(it)
            val simpleDateFormat = SimpleDateFormat("yyyy")
            Text(
                text = "(${simpleDateFormat.format(date)})",
                fontSize = 28.sp,
                style = MaterialTheme.typography.h6
            )
        }
        
        if (genre != null && recordLabel != null) {
            Row(modifier=Modifier.fillMaxSize().padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Surface(
                    color = LightPink, shape = MaterialTheme.shapes.small) {
                    Text(
                        text = genre,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Surface(color = LightOcean, shape = MaterialTheme.shapes.large) {
                    Text(
                        text = recordLabel,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        description?.let {
            Text(text = it, fontSize = 14.sp)
        }
    }
}