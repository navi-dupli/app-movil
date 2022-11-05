package co.navidupli.vinilos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import co.navidupli.vinilos.model.AlbumCreate
import co.navidupli.vinilos.navigation.NavigationRoot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.concurrent.schedule


class AlbumCreateTest {
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
        composeTestRule.setContent {
            NavigationRoot()
        }
    }

    @Test
    fun createAlbum() {
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

        album.description?.let {
            composeTestRule.onNodeWithTag("textFieldAlbumDesc", true).performTextInput(
                it
            )
        }

        album.genre?.let {
            composeTestRule.onNodeWithTag("selectAlbumGenre", true).performClick()
            displayedAlbumGenre()
            composeTestRule.onNodeWithText(it).performClick()
        }

        album.recordLabel?.let {
            composeTestRule.onNodeWithTag("selectAlbumRecordLabel", true).performClick()
            displayedAlbumRecordLabel()
            composeTestRule.onNodeWithText(it).performClick()
        }

        composeTestRule.onNodeWithTag("btnCreateAlbum", true).performClick()

        asyncTimer (10000)
        composeTestRule.onNodeWithTag("btnCreateAlbum", true).assertIsEnabled()
        composeTestRule.onNodeWithTag("textFieldAlbumName", true).assert(hasText(""))
    }

    @Test
    fun createAlbumAndVerifyList() {
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

        album.description?.let {
            composeTestRule.onNodeWithTag("textFieldAlbumDesc", true).performTextInput(
                it
            )
        }

        album.genre?.let {
            composeTestRule.onNodeWithTag("selectAlbumGenre", true).performClick()
            displayedAlbumGenre()
            composeTestRule.onNodeWithText(it).performClick()
        }

        album.recordLabel?.let {
            composeTestRule.onNodeWithTag("selectAlbumRecordLabel", true).performClick()
            displayedAlbumRecordLabel()
            composeTestRule.onNodeWithText(it).performClick()
        }

        composeTestRule.onNodeWithTag("btnCreateAlbum", true).performClick()

        asyncTimer (10000)
        composeTestRule.onNodeWithTag("btnCreateAlbum", true).assertIsEnabled()

        composeTestRule.onNodeWithTag("btn_profile_screen", true).performClick()

        composeTestRule.onNodeWithTag("btnChangeProfile", true).performClick()

        composeTestRule.onNodeWithTag("visitante", true).performClick()

        album.name?.let {
            asyncTimer(20000)
            composeTestRule.onAllNodesWithText(it, true).onFirst().assertIsDisplayed()
        }
    }

    @Test
    fun errorCreateAlbum() {
        val button = composeTestRule.onNode(hasTestTag("coleccionista"), true)
        button.performClick()

        album.name?.let {
            composeTestRule.onNodeWithTag("textFieldAlbumName", true).performTextInput(
                it
            )
        }

        composeTestRule.onNodeWithTag("btnCreateAlbum", true).performClick()

        asyncTimer (10000)
        composeTestRule.onNodeWithTag("btnCreateAlbum", true).assertIsEnabled()
        album.name?.let {
            composeTestRule.onNodeWithTag("textFieldAlbumName", true).assert(hasText(it))
        }
    }

    private fun displayedAlbumGenre() {
        val genres = listOf("Classical", "Salsa", "Rock", "Folk")
        genres.forEach { genre ->
            composeTestRule.onNodeWithText(genre).assertIsDisplayed()
        }
    }

    private fun displayedAlbumRecordLabel() {
        val recordLabels = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
        recordLabels.forEach { recordLabel ->
            composeTestRule.onNodeWithText(recordLabel).assertIsDisplayed()
        }
    }

    private fun asyncTimer (delay: Long = 1000) {
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

}