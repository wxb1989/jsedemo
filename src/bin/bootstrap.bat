@echo off

setlocal

cd ..
set "CURRENT_DIR=%cd%"
set "LIB_DIR=%CURRENT_DIR%\lib"
set "JAVA_COMMAND=java"
cd "%LIB_DIR%"
%JAVA_COMMAND% -jar jse-jar-with-dependencies.jar %1

exit