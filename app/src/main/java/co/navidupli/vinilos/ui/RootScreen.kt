package co.navidupli.vinilos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.navidupli.vinilos.Screen
import co.navidupli.vinilos.R


@Composable
fun RootScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.image_background_root),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        )
        Column(Modifier.align(Alignment.Center)) {
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.AppScaffold.route + "/0") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .testTag("visitante")
            ) {
                Text(text = "Visitante", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(4.dp))
            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.AppScaffold.route + "/1") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .testTag("coleccionista")
            ) {
                Text(text = "Coleccionista", fontSize = 20.sp)
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                text = "VINILOS",
                fontSize = 50.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
