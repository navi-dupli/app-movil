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

class ArtistDetailTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            NavigationRoot()
        }
    }

    @Test
    fun showDetailOfArtist() {
        val button = composeTestRule.onNode(hasTestTag("visitante"), true)
        button.performClick()
        asyncTimer(10000)
        composeTestRule.onNodeWithTag("btn_" + NavigationScreen.ArtistsScreen.route, true)
            .performClick()
        asyncTimer(10000)
        composeTestRule.onNodeWithTag("artistItem_0", true).performClick()
        asyncTimer(10000)
        composeTestRule.onNodeWithTag("detailComponent").assertIsDisplayed()
        composeTestRule.onNodeWithTag("goBack").performClick()
    }

    private fun asyncTimer(delay: Long = 20000) {
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