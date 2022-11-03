package co.navidupli.vinilos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.*
import co.navidupli.vinilos.model.AlbumCreate
import co.navidupli.vinilos.scaffold.AppScaffold
import co.navidupli.vinilos.ui.theme.RootScreen

import org.junit.Test

import org.junit.Before
import org.junit.Rule


class ExampleInstrumentedTest {
    val album = AlbumCreate(
        name= "Juana la cubana",
        description = "Es un album diferente",
        genre = "Rock",
        cover = "https://i.ytimg.com/vi/X_-bKOnS-wA/hqdefault.jpg?sqp=-oaymwEiCKgBEF5IWvKriqkDFQgBFQAAAAAYASUAAMhCPQCAokN4AQ==&rs=AOn4CLDe-R3BYHSf8m5pRBqumzImqlmfbw",
        recordLabel = "EMI",
        releaseDate = "24/5/1999"
    )

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

        album.name?.let {
            composeTestRule.onNodeWithTag("textFieldAlbumName", true).performTextInput(
                it
            )
        }

        album.cover?.let {
            composeTestRule.onNodeWithTag("textFieldAlbumCover", true).performTextInput(
                it
            )
        }

        album.releaseDate?.let {
            val releaseDate = composeTestRule.onNodeWithTag("textFieldAlbumReleaseDate", true)
            releaseDate.performClick()
            releaseDate.performTextInput(it)
        }

        album.description?.let {
            composeTestRule.onNodeWithTag("textFieldAlbumDesc", true).performTextInput(
                it
            )
            composeTestRule.onNodeWithTag("textFieldAlbumDesc", true).assert(hasText(it))
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