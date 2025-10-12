#include <jni.h>
#include <string>
#include <android/log.h>

#define LOG_TAG "NativeCrop"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_reelmakerai_native_NativeBridge_cropVideoNative(JNIEnv *env, jobject thiz, jstring videoPath, jstring cropMode) {
    const char *path = env->GetStringUTFChars(videoPath, nullptr);
    const char *mode = env->GetStringUTFChars(cropMode, nullptr);

    LOGD("Native crop called with path: %s, mode: %s", path, mode);

    // TODO: Implement actual crop logic here using FFmpeg or OpenCV

    env->ReleaseStringUTFChars(videoPath, path);
    env->ReleaseStringUTFChars(cropMode, mode);

    return JNI_TRUE;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_reelmakerai_native_NativeBridge_resizeVideoNative(JNIEnv *env, jobject thiz, jstring videoPath, jstring targetRatio) {
    const char *path = env->GetStringUTFChars(videoPath, nullptr);
    const char *ratio = env->GetStringUTFChars(targetRatio, nullptr);

    LOGD("Native resize called with path: %s, ratio: %s", path, ratio);

    // TODO: Implement actual resize logic here using FFmpeg or OpenCV

    env->ReleaseStringUTFChars(videoPath, path);
    env->ReleaseStringUTFChars(targetRatio, ratio);

    return JNI_TRUE;
}
