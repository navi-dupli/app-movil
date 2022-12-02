package co.navidupli.vinilos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Black500,
    primaryVariant = LightGray700,
    secondary = Teal200,
    onSecondary = Color.White,
    background = Color.White

)
private val DarkColorPalette = lightColors(
    primary =LightGray700 ,
    primaryVariant = Black500,
    secondary = Teal200,
    onSecondary = Color.White,
    background = Black500

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