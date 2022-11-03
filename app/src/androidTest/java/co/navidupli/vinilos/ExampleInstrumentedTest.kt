package co.navidupli.vinilos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.*
import co.navidupli.vinilos.scaffold.AppScaffold
import co.navidupli.vinilos.ui.theme.RootScreen

import org.junit.Test

import org.junit.Before
import org.junit.Rule


class ExampleInstrumentedTest {

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
        composeTestRule.onNodeWithTag("albumes", true).assertIsDisplayed()

    }

    @Test
    fun selectCollectors() {
        val button = composeTestRule.onNode(hasTestTag("coleccionista"), true)

        button.performClick()
        composeTestRule.onNodeWithTag("titleCreateAlbum", true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("textFieldAlbumName", true).performTextInput("album desde test")
        composeTestRule.onNodeWithTag("textFieldAlbumName", true).assert(hasText("album desde test"))
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