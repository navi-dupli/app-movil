package co.navidupli.vinilos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import co.navidupli.vinilos.navigation.NavigationRoot
import co.navidupli.vinilos.navigation.NavigationScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.concurrent.schedule


class AssociateTrackTest {
    private val name = "Mi cancion favorita"
    private val trackDuration = "00:23"

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            NavigationRoot()
        }
    }

    @Test
    fun asociateTrack() {
        val button = composeTestRule.onNode(hasTestTag("coleccionista"), true)
        button.performClick()

        composeTestRule.onNodeWithTag("btn_" + NavigationScreen.AssociateTracksScreen.route, true)
            .performClick()

        asyncTimer(8000)

        "albumItem_0".let {
            composeTestRule.onNodeWithTag("selectAlbum", true).performClick()
            asyncTimer(8000)
            composeTestRule.onNodeWithTag(it).performClick()
        }
        asyncTimer(8000)
        name.let {
            composeTestRule.onNodeWithTag("textFieldTrackName", true).performTextInput(
                it
            )
        }

        trackDuration.let {
            composeTestRule.onNodeWithTag("textFieldTrackDuracion", true).performTextInput(
                it
            )
        }
        composeTestRule.onNodeWithTag("btnSaveAsociateTractToAlbum", true).performClick()

        asyncTimer(5000)
        composeTestRule.onNodeWithTag("btnSaveAsociateTractToAlbum", true).assertIsEnabled()
        composeTestRule.onNodeWithTag("textFieldTrackName", true).assert(hasText(""))
    }

    @Test
    fun errorAsociarTrack() {
        val button = composeTestRule.onNode(hasTestTag("coleccionista"), true)
        button.performClick()

        composeTestRule.onNodeWithTag("btn_" + NavigationScreen.AssociateTracksScreen.route, true)
            .performClick()

        asyncTimer(2000)

        "albumItem_0".let {
            composeTestRule.onNodeWithTag("selectAlbum", true).performClick()
            asyncTimer(1000)
            composeTestRule.onNodeWithTag(it).performClick()
        }
        asyncTimer(1000)
        name.let {
            composeTestRule.onNodeWithTag("textFieldTrackName", true).performTextInput(
                it
            )
        }

        "0000".let {
            composeTestRule.onNodeWithTag("textFieldTrackDuracion", true).performTextInput(
                it
            )
        }
        composeTestRule.onNodeWithTag("btnSaveAsociateTractToAlbum", true).performClick()

        asyncTimer(8000)
        composeTestRule.onNodeWithTag("btnSaveAsociateTractToAlbum", true).assertIsEnabled()
        name.let {
            composeTestRule.onNodeWithTag("textFieldTrackName", true).assert(hasText(it))
        }
    }


    private fun asyncTimer(delay: Long = 1000) {
        AsyncTimer.start(delay)
        composeTestRule.waitUntil(
            condition = { AsyncTimer.expired },
            timeoutMillis = delay + 1000
        )
    }

    object AsyncTimer {
        var expired = false
        fun start(delay: Long = 1000) {
            expired = false
            Timer().schedule(delay) {
                expired = true
            }
        }
    }

}