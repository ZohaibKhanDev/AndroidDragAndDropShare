@file:OptIn(ExperimentalFoundationApi::class)

package com.yanneckreiss.contentreceiveapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yanneckreiss.contentreceiveapp.ui.theme.AndroidTutorialTemplateTheme


@Composable
fun ReceiveContentScreen(
    viewModel: ReceiveContentViewModel = viewModel { ReceiveContentViewModel() }
) {

    val state: ReceiveContentState by viewModel.state.collectAsStateWithLifecycle()

    ReceiveContentScreenContent(
        text = state.text,
        onDragAndDropEventReceived = { event: DragAndDropEvent ->
            viewModel.updateTextByClipData(event.toAndroidDragEvent().clipData)
        },
        onDragAnDropStarted = { viewModel.updateDragAndDropHint(shouldShowHint = true) },
        onDragAnDropEnded = { viewModel.updateDragAndDropHint(shouldShowHint = false) },
        showDragAndDropHint = state.showDragAndDropHint
    )
}

@Composable
private fun ReceiveContentScreenContent(
    text: String,
    onDragAndDropEventReceived: (DragAndDropEvent) -> Unit,
    onDragAnDropStarted: () -> Unit,
    onDragAnDropEnded: () -> Unit,
    showDragAndDropHint: Boolean
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .dragAndDropTarget(
                shouldStartDragAndDrop = { startEvent: DragAndDropEvent ->
                    startEvent
                        .mimeTypes()
                        .any { it.startsWith("text/") }
                },
                target = object : DragAndDropTarget {
                    override fun onDrop(event: DragAndDropEvent): Boolean {
                        onDragAndDropEventReceived(event)
                        return true
                    }

                    override fun onStarted(event: DragAndDropEvent) {
                        onDragAnDropStarted()
                    }

                    override fun onEnded(event: DragAndDropEvent) {
                        onDragAnDropEnded()
                    }
                }
            )
            .systemBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "\uD83D\uDCC1 Drop your content down below",
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        if (showDragAndDropHint) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text
        )
    }
}

@Composable
@Preview
private fun Preview_ReceiveContentScreen() {
    AndroidTutorialTemplateTheme {
        Surface {
            ReceiveContentScreenContent(
                text = "hello",
                onDragAndDropEventReceived = {},
                onDragAnDropStarted = {},
                onDragAnDropEnded = {},
                showDragAndDropHint = false
            )
        }
    }
}

@Composable
@Preview
private fun Preview_ReceiveContentScreen_HintActive() {
    AndroidTutorialTemplateTheme {
        Surface {
            ReceiveContentScreenContent(
                text = "hello",
                onDragAndDropEventReceived = {},
                onDragAnDropStarted = {},
                onDragAnDropEnded = {},
                showDragAndDropHint = true
            )
        }
    }
}