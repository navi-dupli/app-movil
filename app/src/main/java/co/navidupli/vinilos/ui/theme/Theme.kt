package co.navidupli.vinilos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Black500,
    primaryVariant = LightGray700,
    secondary = Teal200

)
private val DarkColorPalette = lightColors(
    primary = Black500,
    primaryVariant = LightGray700,
    secondary = Teal200

)

@Composable
fun VinilosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors: Colors = if(darkTheme){
        DarkColorPalette
    }else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}