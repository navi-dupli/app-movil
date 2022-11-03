package co.navidupli.vinilos.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.navidupli.vinilos.Screen

@Composable
fun RootScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
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
            Button(
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
        }
    }
}
