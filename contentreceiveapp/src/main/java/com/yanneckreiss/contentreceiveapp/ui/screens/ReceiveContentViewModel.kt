package com.yanneckreiss.contentreceiveapp.ui.screens

import android.content.ClipData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReceiveContentViewModel : ViewModel() {

    private val _state = MutableStateFlow(ReceiveContentState())
    val state: StateFlow<ReceiveContentState> = _state.asStateFlow()

    fun updateTextByClipData(clipData: ClipData?) {

        if (clipData == null) return

        val sharedText: String = (0 until clipData.itemCount)
            .map { index -> clipData.getItemAt(index).text }
            .filter { text -> text.isNotBlank() }
            .joinToString(separator = "\n")

        if (sharedText.isNotBlank()) {
            _state.update { current -> current.copy(text = sharedText) }
        }
    }

    fun updateDragAndDropHint(shouldShowHint: Boolean) {
        _state.update { current -> current.copy(showDragAndDropHint = shouldShowHint) }
    }
}
