package co.navidupli.vinilos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(text: String, testTag: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.testTag(testTag)
    )
}

@Composable
fun Space(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}
