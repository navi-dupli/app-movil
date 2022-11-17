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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage

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
            .testTag(testTag ?: "")
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

                    GlideImage(
                        imageModel = imageUrl,
                        requestBuilder = Glide
                            .with(LocalView.current)
                            .asBitmap()
                            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                            .thumbnail(0.1f)
                            .transition(withCrossFade()),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(90.dp)
                            .testTag("imageCard").aspectRatio(0.9f)
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
