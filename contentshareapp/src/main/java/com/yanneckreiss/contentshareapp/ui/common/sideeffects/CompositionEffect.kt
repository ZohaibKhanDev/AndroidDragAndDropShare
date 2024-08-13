package com.yanneckreiss.contentshareapp.ui.common.sideeffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember

/**
 * Starts when the composition becomes present for the first time.
 * Basically the same as [androidx.compose.runtime.LaunchedEffect] except it doesn't launch a coroutine.
 *
 * @author https://twitter.com/Zhuinden/status/1758981610743943237
 */
private class CompositionEffectImpl(
    private val effect: () -> Unit
) : RememberObserver {
    override fun onAbandoned() {}

    override fun onForgotten() {}

    override fun onRemembered() {
        effect()
    }
}

@Composable
@NonRestartableComposable
fun CompositionEffect(effect: () -> Unit) {
    remember { CompositionEffectImpl(effect) }
}

@Composable
@NonRestartableComposable
fun CompositionEffect(key: Any?, effect: () -> Unit) {
    remember(key) { CompositionEffectImpl(effect) }
}

@Composable
@NonRestartableComposable
fun CompositionEffect(vararg keys: Any?, effect: () -> Unit) {
    remember(*keys) { CompositionEffectImpl(effect) }
}
