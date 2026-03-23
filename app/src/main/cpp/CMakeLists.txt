cmake_minimum_required(VERSION 3.4.1)

project(PokeFinderAndroid)

option(TEST "Run Tests" OFF)
option(SIMD "Enable SIMD" ON)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

list(APPEND CMAKE_MODULE_PATH "${CMAKE_CURRENT_LIST_DIR}/CMake")

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

include_directories(${CMAKE_SOURCE_DIR})
include_directories(${CMAKE_SOURCE_DIR}/Core)
include_directories(${CMAKE_SOURCE_DIR}/Core/External/nlohmann)
include_directories(${CMAKE_SOURCE_DIR}/Core/External/fph)

add_subdirectory(Core/External)

# Collect all .cpp files from Core directory, excluding external contrib
file(GLOB_RECURSE CORE_SOURCES
    "Core/*.cpp"
)
# Remove test and contrib files
list(FILTER CORE_SOURCES EXCLUDE REGEX ".*contrib.*")
list(FILTER CORE_SOURCES EXCLUDE REGEX ".*test.*")

add_library( pokefinder_jni SHARED
    gen3_wrapper.cpp
    ${CORE_SOURCES}
)

find_library( log-lib log )

target_link_libraries( pokefinder_jni libzstd_static ${log-lib} )
