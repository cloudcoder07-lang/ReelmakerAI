package com.reelmakerai.util

import android.net.Uri
import android.util.Log
import com.reelmakerai.data.EditHistoryRepository
import com.reelmakerai.model.VideoEditState

object EditHistoryUtils {

    private var lastSavedState: VideoEditState? = null

    /**
     * Saves the edit state unconditionally and updates the last saved reference.
     */
    fun saveHistory(state: VideoEditState) {
        EditHistoryRepository.saveEdit(state)
        lastSavedState = state
        Log.d("EditHistory", "Manually saved: $state")
    }

    /**
     * Saves the current edit state only if it differs from the last saved state.
     */
    fun smartSave(currentUri: Uri, aspectRatio: Float, toolUsed: String = "Unknown") {
        val newState = VideoEditState(
            videoUri = currentUri,
            aspectRatio = aspectRatio,
            toolUsed = toolUsed,
            timestamp = System.currentTimeMillis()
        )

        if (hasChanged(newState)) {
            saveHistory(newState) // ✅ Now safe: defined above
        } else {
            Log.d("SmartSave", "No changes detected. Skipping save.")
        }
    }

    private fun hasChanged(newState: VideoEditState): Boolean {
        val last = lastSavedState ?: return true
        return newState.videoUri != last.videoUri ||
                newState.aspectRatio != last.aspectRatio ||
                newState.toolUsed != last.toolUsed
    }
}
