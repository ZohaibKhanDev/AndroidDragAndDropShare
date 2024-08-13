package com.yanneckreiss.contentshareapp.ui.common.events

import androidx.compose.runtime.Immutable

@Immutable
sealed interface OneTimeEvent<out T> {

    @Immutable
    data class Invoked<T>(
        val data: T,
        val onConsumed: () -> Unit,
    ) : OneTimeEvent<T>

    @Immutable
    data object Consumed : OneTimeEvent<Nothing>
}
