@echo off

set MAVEN_OPTS="-Dmaven.multiModuleProjectDirectory=%~dp0.."

"C:\Users\malli\.jdks\openjdk-24.0.1\bin\java" %MAVEN_OPTS% -jar "%~dp0.mvn\wrapper\maven-wrapper.jar" %*
