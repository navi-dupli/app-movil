package co.navidupli.vinilos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import co.navidupli.vinilos.navigation.NavigationRoot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.concurrent.schedule


class AlbumListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            NavigationRoot()
        }
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

    private fun asyncTimer (delay: Long = 20000) {
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