package com.reelmakerai.tools

import com.reelmakerai.model.ToolType
import com.reelmakerai.model.SubToolItem
import com.reelmakerai.ui.AIStudioActivity
import android.content.Context

object ToolDispatcher {

    fun dispatch(context: Context, toolType: ToolType, subTool: SubToolItem) {
        when (toolType) {
            ToolType.CANVAS -> {
                if (context is AIStudioActivity) {
                    when (subTool.label) {
                        "Resize" -> context.launchToolEditor("Resize")
                        "Crop" -> context.launchToolEditor("Crop")
                        "Rotate" -> context.launchToolEditor("Rotate")
                        "Flip" -> context.launchToolEditor("Flip")
                        "Zoom" -> context.launchToolEditor("Zoom")
                        "BGFill" -> context.launchToolEditor("BGFill")
                    }
                }
            }

            ToolType.AUDIO -> when (subTool.label) {
                "Add Music" -> AudioTool.addMusic("Library")
                "Voiceover" -> AudioTool.voiceover("Record")
                "Sound Effects" -> AudioTool.soundEffect("Pop")
                "Volume Control" -> AudioTool.volumeControl(true, true, true)
                "Sync to Beat" -> AudioTool.syncToBeat()
            }

            ToolType.STICKER -> when (subTool.label) {
                "Emoji Pack" -> StickerTool.loadEmojiPack()
                "Animated Stickers" -> StickerTool.loadAnimatedStickers()
                "Branded Overlays" -> StickerTool.loadBrandedOverlays()
                "Custom Upload" -> StickerTool.uploadCustomSticker("uri://example")
                "Position & Scale" -> StickerTool.positionAndScale(0.5f, 0.5f, 1.0f)
            }

            ToolType.TEXT -> when (subTool.label) {
                "Add Text" -> TextTool.addText("Hello World")
                "Font Picker" -> TextTool.pickFont("Roboto")
                "Color & Gradient" -> TextTool.setColorGradient(listOf(0xFF0000, 0x0000FF))
                "Animation" -> TextTool.animate("Fade")
                "Timing" -> TextTool.setTiming(0, 5000)
            }

            ToolType.EFFECT -> when (subTool.label) {
                "Glitch" -> EffectTool.applyGlitch()
                "Blur" -> EffectTool.applyBlur(0.5f)
                "Shake" -> EffectTool.applyShake()
                "VHS" -> EffectTool.applyVHS()
                "Sparkle" -> EffectTool.applySparkle()
                "Custom FX" -> EffectTool.applyCustomFX("<xml></xml>")
            }

            ToolType.FILTER -> when (subTool.label) {
                "Mood Presets" -> FilterTool.applyMoodPreset("Warm")
                "LUTs" -> FilterTool.applyLUT("Teal-Orange")
                "Intensity Slider" -> FilterTool.setIntensity(0.8f)
                "Preview Grid" -> FilterTool.showPreviewGrid()
            }

            ToolType.VOICE_FX -> when (subTool.label) {
                "Robot" -> VoiceFxTool.applyRobot()
                "Chipmunk" -> VoiceFxTool.applyChipmunk()
                "Deep Voice" -> VoiceFxTool.applyDeepVoice()
                "Echo" -> VoiceFxTool.applyEcho()
                "Reverb" -> VoiceFxTool.applyReverb()
                "Custom Pitch" -> VoiceFxTool.applyCustomPitch(1.2f)
            }

            ToolType.CUT -> when (subTool.label) {
                "Trim Start/End" -> CutTool.trim(0, 5000)
                "Split at Playhead" -> CutTool.splitAt(3000)
                "Multi-Segment Cut" -> CutTool.multiSegmentCut(listOf(0 to 2000, 4000 to 6000))
                "Ripple Delete" -> CutTool.rippleDelete(2000 to 3000)
                "Preview Before Cut" -> CutTool.previewCut(1000 to 3000)
            }

            ToolType.MERGE -> when (subTool.label) {
                "Add Video" -> MergeTool.addVideo("uri://video")
                "Add Photo" -> MergeTool.addPhoto("uri://photo")
                "Insert at Playhead" -> MergeTool.insertAt(3000, "uri://insert")
                "Append to End" -> MergeTool.appendToEnd("uri://append")
                "Transition Options" -> MergeTool.setTransition("Fade")
            }
        }
    }
}
