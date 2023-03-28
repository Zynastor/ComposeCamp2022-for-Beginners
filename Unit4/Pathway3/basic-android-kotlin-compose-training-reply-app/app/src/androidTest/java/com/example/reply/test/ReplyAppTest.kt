package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.R.*
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {
    @get:Rule
    val compostTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {
        // Set up compact window
        compostTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }
        // Bottom Navigation is displayed.
        compostTestRule.onNodeWithTagForStringId(
            string.navigation_bottom
        ).assertExists()
    }
    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingBottomNavigation() {
        // Set up compact window
        compostTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }
        // Bottom Navigation is displayed.
        compostTestRule.onNodeWithTagForStringId(
            string.navigation_rail
        ).assertExists()
    }
    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail(){
        // Set up medium window
        compostTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }
        // Navigation rail is displayed
        compostTestRule.onNodeWithTagForStringId(
            string.navigation_rail
        ).assertExists()
    }

    @Test
    @TestCompactWidth
    fun expandedDevice_verifyUsingBottomNavigation() {
        // Set up compact window
        compostTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }
        // Bottom Navigation is displayed.
        compostTestRule.onNodeWithTagForStringId(
            string.navigation_drawer
        ).assertExists()
    }
}