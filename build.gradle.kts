// build.gradle.kts (project-level)

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false // Update to latest stable version as needed
    id("com.android.application") version "8.2.2" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
}
