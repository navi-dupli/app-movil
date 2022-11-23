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


class CollectorsListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            NavigationRoot()
        }
    }

    @Test
    fun listarColeccionistas() {
        val button = composeTestRule.onNode(hasTestTag("visitante"), true)

        button.performClick()

        composeTestRule.onNodeWithTag("btn_" + NavigationScreen.CollectorsScreen.route, true)
            .performClick()
        composeTestRule.onNodeWithTag("titleCollectors", true).assertTextEquals("Collectors")
        asyncTimer(2000)

        composeTestRule.onAllNodesWithTag("tittleCard", true).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("dateCard", true).onFirst().assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("subtextCard", true).onFirst().assertIsDisplayed()
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