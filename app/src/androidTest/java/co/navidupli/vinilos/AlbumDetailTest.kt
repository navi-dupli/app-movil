package co.navidupli.vinilos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import co.navidupli.vinilos.navigation.NavigationRoot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.concurrent.schedule

class AlbumDetailTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            NavigationRoot()
        }
    }

    @Test
    fun showDetailOfAlbum() {
        val button = composeTestRule.onNode(hasTestTag("visitante"), true)
        button.performClick()
        asyncTimer()
        composeTestRule.onAllNodesWithTag("card").onFirst().performClick()
        asyncTimer(10000)
        composeTestRule.onNodeWithTag("detailComponent").assertIsDisplayed()
        composeTestRule.onNodeWithTag("goBack").performClick()
    }

    fun asyncTimer(delay: Long = 20000) {
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