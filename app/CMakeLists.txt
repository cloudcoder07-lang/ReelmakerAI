cmake_minimum_required(VERSION 3.10)
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,--page-size=16384")
project(reelmaker_native)

add_library(reelmaker_native SHARED
        src/main/cpp/LutShaderRenderer.cpp
        src/main/cpp/ShaderUtils.cpp
        src/main/cpp/VoiceTrigger.cpp
        src/main/cpp/WatermarkOverlay.cpp
)

find_library(log-lib log)
find_library(android-lib android)
find_library(GLESv2-lib GLESv2)

target_link_libraries(reelmaker_native
        ${log-lib}
        ${android-lib}
        ${GLESv2-lib}
)
