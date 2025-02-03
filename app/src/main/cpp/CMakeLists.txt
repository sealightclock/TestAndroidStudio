cmake_minimum_required(VERSION 3.18.1)
project("hello")

add_library(
    hello
    SHARED
    hello.cpp)

find_library(
    log-lib
    log)

target_link_libraries(
    hello
    ${log-lib})
