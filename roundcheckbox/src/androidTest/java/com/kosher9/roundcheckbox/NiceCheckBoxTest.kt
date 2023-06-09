package com.kosher9.roundcheckbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test

class NiceCheckBoxTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkBoxDisabledShouldNotClick() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RoundCheckBox(
                            isChecked = false,
                            onClick = {},
                            enabled = false,
                            modifier = Modifier.semantics { contentDescription = "checkbox" })
                    }
                }
            }
        }
        composeTestRule.onNodeWithContentDescription("checkbox").assertIsNotEnabled()
        composeTestRule.onNodeWithContentDescription("checkbox").assertIsOff()
        composeTestRule.onNodeWithContentDescription("checkbox").assertHasClickAction()
    }

    @Test
    fun checkBoxEnabledShouldClick() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var state by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RoundCheckBox(
                            isChecked = state,
                            onClick = { state = !state },
                            enabled = true,
                            modifier = Modifier.semantics { contentDescription = "checkbox" })
                    }
                }
            }
        }
        composeTestRule.onNodeWithContentDescription("checkbox").assertIsEnabled()
        composeTestRule.onNodeWithContentDescription("checkbox").performClick()
        composeTestRule.onNodeWithContentDescription("checkbox").assertIsOn()
        composeTestRule.onNodeWithContentDescription("checkbox").assertHasClickAction()
    }
}