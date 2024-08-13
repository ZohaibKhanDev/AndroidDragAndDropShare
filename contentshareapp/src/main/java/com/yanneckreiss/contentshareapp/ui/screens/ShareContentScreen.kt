@file:OptIn(ExperimentalFoundationApi::class)

package com.yanneckreiss.contentshareapp.ui.screens

import android.content.ClipData
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yanneckreiss.contentshareapp.ui.theme.TutorialTheme

@Composable
fun ShareContentScreen() {

    ShareContentScreenContent(
        text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
    )
}

@Composable
private fun ShareContentScreenContent(
    text: String,
) {

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "\uD83D\uDD17 Share the content down below",
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            modifier = Modifier.dragAndDropSource {
                  detectTapGestures(
                      onLongPress = {
                          startTransfer(text.toDragAndDropData())
                      }
                  )
            },
            text = text
        )
    }
}

private fun String.toDragAndDropData() = DragAndDropTransferData(
    clipData = ClipData.newPlainText("ShareText", this),
    flags = View.DRAG_FLAG_GLOBAL
)

@Composable
@Preview
private fun Preview_ReceiveContentScreen() {
    TutorialTheme {
        Surface {
            ShareContentScreenContent(
                text = "hello"
            )
        }
    }
}