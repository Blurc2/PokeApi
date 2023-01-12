import AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT
import AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT_KTX
import AndroidTestingLib.MOCKITO
import AndroidTestingLib.MOCKITO_INLINE
import AndroidTestingLib.MOCKITO_KOTLIN
import AppCoordinates.APP_ID
import AppCoordinates.APP_VERSION_CODE
import AppCoordinates.APP_VERSION_NAME
import AppCoordinates.COMPILE_SDK_VERSION
import AppCoordinates.MIN_SDK_VERSION
import AppCoordinates.TARGET_SDK_VERSION
import Dependencies.BARCODE_SCANNER
import Dependencies.BARCODE_SCANNER_CORE
import Dependencies.CAMERAX
import Dependencies.CAMERAX_LIFECYCLE
import Dependencies.CAMERAX_VIEW
import Dependencies.CIRCLE_PROGRESS_BAR
import Dependencies.CROPPER
import Dependencies.GSON
import Dependencies.LOGGING_INTERCEPTR
import Dependencies.LOTTIE
import Dependencies.MOSHI_CONVERTER
import Dependencies.OK_HTTP
import Dependencies.RETROFIT
import Dependencies.ROOM
import Dependencies.ROOM_COMPILER
import Dependencies.ROOM_KTX
import Dependencies.SECURITY_CRYPTO
import Dependencies.SHIMMER
import Dependencies.SQLCIPHER
import Dependencies.SQLITE_KTX
import Dependencies.STETHO
import RaerLibs.ARTIFACTS
import RaerLibs.CAMERA
import RaerLibs.SURVEY
import RaerLibs.TRACKING
import RaerLibs.UTILS
import SupportLibs.ANDROIDX_APPCOMPAT
import SupportLibs.ANDROIDX_CONSTRAINT_LAYOUT
import SupportLibs.ANDROIDX_CORE_KTX
import SupportLibs.ANDROIDX_LIFECYCLE_LIVE_DATA
import SupportLibs.ANDROIDX_LIFECYCLE_VIEW_MODEL
import SupportLibs.ANDROIDX_NAVIGATION_FRAGMENT
import SupportLibs.ANDROIDX_NAVIGATION_UI
import SupportLibs.COROUTINES
import SupportLibs.MATERIAL
import Testing.JUNIT
import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("androidx.navigation.safeargs")

}

android {
    compileSdk = COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = APP_ID
        minSdk = MIN_SDK_VERSION
        targetSdk = TARGET_SDK_VERSION
        versionCode = APP_VERSION_CODE
        versionName = APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(ANDROIDX_CORE_KTX)
    implementation(ANDROIDX_APPCOMPAT)
    implementation(MATERIAL)
    implementation(ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(ANDROIDX_LIFECYCLE_VIEW_MODEL)
    implementation(ANDROIDX_LIFECYCLE_LIVE_DATA)
    implementation(ANDROIDX_NAVIGATION_FRAGMENT)
    implementation(ANDROIDX_NAVIGATION_UI)
    implementation(COROUTINES)
    implementation(RETROFIT)
    implementation(OK_HTTP)
    implementation(LOGGING_INTERCEPTR)
    implementation(MOSHI_CONVERTER)
    implementation(ROOM)
    implementation(ROOM_KTX)
    implementation(SECURITY_CRYPTO)
    implementation(SQLITE_KTX)
    implementation(SQLCIPHER)
    implementation(STETHO)
    implementation(LOTTIE)
    implementation(CAMERAX)
    implementation(CAMERAX_LIFECYCLE)
    implementation(CAMERAX_VIEW)
    implementation(BARCODE_SCANNER)
    implementation(BARCODE_SCANNER_CORE)
    implementation(CIRCLE_PROGRESS_BAR)
    implementation(SHIMMER)
    implementation(GSON)

    implementation(UTILS)
    implementation(ARTIFACTS)
    implementation(SURVEY)
    implementation(TRACKING)
    implementation(CAMERA)

    api(CROPPER)

    kapt(ROOM_COMPILER)

    testImplementation(JUNIT)
    testImplementation(MOCKITO)
    testImplementation(MOCKITO_KOTLIN)
    testImplementation(MOCKITO_INLINE)

    androidTestImplementation(ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(ANDROIDX_TEST_EXT_JUNIT_KTX)
}