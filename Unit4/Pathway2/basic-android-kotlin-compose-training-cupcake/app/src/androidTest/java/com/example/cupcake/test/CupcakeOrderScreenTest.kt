package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.R.*
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeOrderUiState = OrderUiState(
        quantity = 6,
        flavor = "Vanilla",
        date = "Wed Jul 21",
        price = "$100",
        pickupOptions = listOf()
    )

    @Test
    fun selectedOptionScreen_verifyContent() {
        // Given list of options
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And sub total
        val subTotal = "$100"
        // When SelectedOptionScreen is loaded.
        composeTestRule.setContent {
            CupcakeTheme {
                SelectOptionScreen(subtotal = subTotal, options = flavours)
            }
        }
        // Then all the options are displayed on the screen.
        flavours.forEach { flavour ->
            composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }
        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                string.subtotal_price, subTotal
            )
        ).assertIsDisplayed()
        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(string.next).assertIsNotEnabled()
    }

    /**
     * When a OrderUiState is provided to Summary Screen, the the flavor, date and subtotal is
     * displayed on the screen.
     */
    @Test
    fun summaryScreen_verifyContentDisplay() {
        // When summary Screen is loaded
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = fakeOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> }
            )
        }
        // Then the Ui is updated correctly.
        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                string.subtotal_price,
                fakeOrderUiState.price
            )
        )
    }
}