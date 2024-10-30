plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
   // id("com.google.devtools.ksp") version "1.9.23-1.0.20"
    id(libs.plugins.googleKsp.get().pluginId)

}

android {
    namespace = "com.shaik.pennypilot.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shaik.pennypilot.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Update to Java 11 or 17 as needed
        targetCompatibility = JavaVersion.VERSION_11 // Update to Java 11 or 17 as needed
    }

    kotlinOptions {
        jvmTarget = "11" // Update JVM target to "11" or "17" as needed
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Jetpack Compose dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Dagger Hilt with KSP instead of kapt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room Database dependencies
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    implementation("androidx.room:room-ktx:$room_version")

    // Navigation Component for Compose
    val nav_version = "2.8.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // MPAndroidChart or an alternative
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0") // Ensure it's sourced from MavenCentral or JitPack

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.compose.compiler:compiler:1.5.1")
}
