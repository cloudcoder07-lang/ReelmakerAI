cmake_minimum_required(VERSION 3.10)

project("subtools")

add_library(
        subtools
        SHARED
        native_bridge.cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        subtools
        ${log-lib})
