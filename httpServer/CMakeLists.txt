cmake_minimum_required(VERSION 3.13)
project(project C)

set(CMAKE_C_STANDARD 11)

#set(CMAKE_C_EXTENSIONS OFF)

if (MSVC)
    # warning level 4 and all warnings as errors
    add_compile_options(/W4 /WX)
else()
    # lots of warnings and all warnings as errors
    add_compile_options(-Wall -Wextra -pedantic -Werror)
endif()

add_executable(project main.c)