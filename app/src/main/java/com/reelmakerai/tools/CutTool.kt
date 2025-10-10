package com.reelmakerai.tools

object CutTool {
    fun trim(startMs: Int, endMs: Int) {}
    fun splitAt(playheadMs: Int) {}
    fun multiSegmentCut(segments: List<Pair<Int, Int>>) {}
    fun rippleDelete(segment: Pair<Int, Int>) {}
    fun previewCut(segment: Pair<Int, Int>) {}
}
