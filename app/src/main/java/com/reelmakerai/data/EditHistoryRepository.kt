package com.reelmakerai.data

import android.content.Context
import com.reelmakerai.model.VideoEditState

object EditHistoryRepository {
    private val historyList = mutableListOf<VideoEditState>()

    fun loadHistory(): List<VideoEditState> = historyList

    fun saveEdit(state: VideoEditState) {
        historyList.removeAll { it.videoUri == state.videoUri }
        historyList.add(0, state)
    }
}
