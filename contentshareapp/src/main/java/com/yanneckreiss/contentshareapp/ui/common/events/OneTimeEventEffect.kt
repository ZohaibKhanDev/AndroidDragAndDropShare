package com.yanneckreiss.contentshareapp.ui.common.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import kotlinx.coroutines.CoroutineScope

@NonRestartableComposable
@Composable
fun <T> OneTimeEffect(
    event: OneTimeEvent<T>,
    block: suspend CoroutineScope.(OneTimeEvent.Invoked<T>) -> Unit
) {

    LaunchedEffect(event is OneTimeEvent.Invoked) {
        (event as? OneTimeEvent.Invoked)?.let { invokedOneTimeEvent ->
            block(invokedOneTimeEvent)
        }
    }
}
