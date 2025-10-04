package com.reelmakerai.release

object ReleaseLock {
    var isLocked = true

    fun unlock() {
        isLocked = false
    }

    fun lock() {
        isLocked = true
    }
}
