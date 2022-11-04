package co.navidupli.vinilos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.*
import co.navidupli.vinilos.model.AlbumCreate
import co.navidupli.vinilos.scaffold.AppScaffold
import co.navidupli.vinilos.ui.theme.RootScreen
import okhttp3.internal.wait

import org.junit.Test

import org.junit.Before
import org.junit.Rule
import java.util.*
import kotlin.concurrent.schedule


class AlbumListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setupNavHost() {
        launchRegisterScreenWithNavGraph()
    }

    @Test
    fun selectVisitors() {
        val button = composeTestRule.onNode(hasTestTag("visitante"), true)

        button.performClick()
        composeTestRule.onNodeWithTag("titleAlbum", true).assertTextEquals("Álbumes")

        asyncTimer()
        composeTestRule.onAllNodesWithTag("tittleCard", true).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("dateCard", true).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("subtextCard", true).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("imageCard", true).onFirst().assertIsDisplayed()
    }

    fun asyncTimer (delay: Long = 20000) {
        AsyncTimer.start (delay)
        composeTestRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
    }

    object AsyncTimer {
        var expired = false
        fun start(delay: Long = 1000){
            expired = false
            Timer().schedule(delay) {
                expired = true
            }
        }
    }



    private fun launchRegisterScreenWithNavGraph() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val navBarNavController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.RootScren.route) {
                composable(
                    route = Screen.RootScren.route,
                    content = {
                        RootScreen(navController)
                    })
                composable(
                    route = Screen.AppScaffold.route+ "/{type}",
                    arguments = listOf(navArgument("type"){
                        type = NavType.IntType
                    }),
                    content = {
                        AppScaffold(navController = navBarNavController, it.arguments?.getInt("type")) {
                            navController.navigate(Screen.RootScren.route)
                        }
                    }
                )
            }
        }
    }

}