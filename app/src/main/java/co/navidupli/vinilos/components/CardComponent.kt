package co.navidupli.vinilos.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ComponentCard(
    tittle: String,
    date: String?,
    subtext: String?,
    imageUrl: String?,
    onClick: () -> Unit,
    testTag: String?
) {
    Card(
        modifier = Modifier
            .testTag(testTag!!)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{ onClick() },
        elevation = 10.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (imageUrl != null) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Image(
                        imageUrl = imageUrl,
                        contentDescription = tittle,
                        modifier = Modifier
                            .size(90.dp)
                            .testTag("imageCard")

                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = tittle,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .width(1000.dp)
                        .testTag("tittleCard")

                )
                if (date != null) {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .heightIn(min = 20.dp)
                            .wrapContentHeight()
                            .testTag("dateCard")

                    )
                }
                if (subtext != null) {
                    Text(
                        text = subtext,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .heightIn(min = 20.dp)
                            .wrapContentHeight()
                            .testTag("subtextCard")

                    )
                }


            }
        }
    }
}

@Composable
fun Image(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}