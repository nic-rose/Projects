# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/clion-2018.3.2/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /opt/clion-2018.3.2/bin/cmake/linux/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/project.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/project.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/project.dir/flags.make

CMakeFiles/project.dir/main.c.o: CMakeFiles/project.dir/flags.make
CMakeFiles/project.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/project.dir/main.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/project.dir/main.c.o   -c /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/main.c

CMakeFiles/project.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/project.dir/main.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/main.c > CMakeFiles/project.dir/main.c.i

CMakeFiles/project.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/project.dir/main.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/main.c -o CMakeFiles/project.dir/main.c.s

# Object files for target project
project_OBJECTS = \
"CMakeFiles/project.dir/main.c.o"

# External object files for target project
project_EXTERNAL_OBJECTS =

project: CMakeFiles/project.dir/main.c.o
project: CMakeFiles/project.dir/build.make
project: CMakeFiles/project.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable project"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/project.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/project.dir/build: project

.PHONY : CMakeFiles/project.dir/build

CMakeFiles/project.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/project.dir/cmake_clean.cmake
.PHONY : CMakeFiles/project.dir/clean

CMakeFiles/project.dir/depend:
	cd /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/cmake-build-debug /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/cmake-build-debug /home/CS/users/nrose/.linux/College_Classes/nrose/course/cos331/project/HTTP/cmake-build-debug/CMakeFiles/project.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/project.dir/depend

