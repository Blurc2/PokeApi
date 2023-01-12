internal object Versions {
    const val ANDROIDX_TEST_EXT_VERSION = "1.1.3"
    const val APPCOMPAT_VERSION = "1.3.1"
    const val CONSTRAINT_LAYOUT_VERSION = "2.1.1"
    const val CORE_KTX_VERSION = "1.7.0"
    const val LIFECYCLE_VERSION = "2.4.0"
    const val NAVIGATION_VERSION = "2.3.5"
    const val JUNIT_VERSION = "4.13"
    const val MATERIAL_VERSION = "1.4.0"
    const val MOCKITO_VERSION = "4.0.0"
    const val MOCKITO_KOTLIN_VERSION = "2.2.0"
    const val MOCKITO_INLINE_VERSION = "3.4.6"
    const val RETROFIT_VERSION = "2.9.0"
    const val OK_HTTP_VERSION = "4.9.3"
    const val COROUTINES_VERSION = "1.5.2"
    const val ROOM_VERSION = "2.4.3"
    const val SQLCIPHER_VERSION = "4.4.2"
    const val SQLITE_KTX_VERSION = "2.1.0"
    const val SECURITY_CRYPTO_VERSION = "1.1.0-alpha03"
    const val STETHO_VERSION = "1.5.1"
    const val CAMERAX_VERSION = "1.1.0-rc01"
    const val CROPPER_VERSION = "2.8.0"
    const val LOTTIE_VERSION = "3.6.0"
    const val BARCODE_SCANNER_VERSION = "4.2.0"
    const val BARCODE_SCANNER_CORE_VERSION = "3.3.0"
    const val CIRCLE_PROGRESS_BAR_VERSION = "1.3.6"
    const val SHIMMER_VERSION = "0.5.0"
    const val GSON_VERSION = "2.8.6"

    const val SURVEY_VERSION = "1.0.7"
    const val UTILS_VERSION = "1.2.0"
    const val TRACKING_VERSION = "1.0.0"
    const val CAMERA_VERSION = "1.0.0"
    const val ARTIFACTS_VERSION = "1.1.7"
}

object RaerLibs {
    const val SURVEY = "com.raer.artifacts:surveyapi:${Versions.SURVEY_VERSION}:debug@aar"
    const val UTILS = "com.raer.artifacts:utils:${Versions.UTILS_VERSION}:debug@aar"
    const val TRACKING = "com.raer.artifacts:Tracking:${Versions.TRACKING_VERSION}:debug@aar"
    const val CAMERA = "com.raer.artifacts:CameraApi:${Versions.CAMERA_VERSION}:debug@aar"
    const val ARTIFACTS = "com.raer.artifacts:artifacts:${Versions.ARTIFACTS_VERSION}:debug@aar"
}

object SupportLibs {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    const val ANDROIDX_LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}"
    const val ANDROIDX_LIFECYCLE_LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_VERSION}"
    const val ANDROIDX_NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_VERSION}"
    const val ANDROIDX_NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_VERSION}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"
}

object Dependencies {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.OK_HTTP_VERSION}"
    const val LOGGING_INTERCEPTR = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP_VERSION}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_VERSION}"
    const val ROOM = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    const val SQLCIPHER = "net.zetetic:android-database-sqlcipher:${Versions.SQLCIPHER_VERSION}"
    const val SQLITE_KTX = "androidx.sqlite:sqlite-ktx:${Versions.SQLITE_KTX_VERSION}"
    const val SECURITY_CRYPTO = "androidx.security:security-crypto:${Versions.SECURITY_CRYPTO_VERSION}"
    const val STETHO = "com.facebook.stetho:stetho:${Versions.STETHO_VERSION}"
    const val CAMERAX = "androidx.camera:camera-camera2:${Versions.CAMERAX_VERSION}"
    const val CAMERAX_LIFECYCLE = "androidx.camera:camera-lifecycle:${Versions.CAMERAX_VERSION}"
    const val CAMERAX_VIEW = "androidx.camera:camera-view:${Versions.CAMERAX_VERSION}"
    const val CROPPER = "com.theartofdev.edmodo:android-image-cropper:${Versions.CROPPER_VERSION}"
    const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE_VERSION}"
    const val BARCODE_SCANNER = "com.journeyapps:zxing-android-embedded:${Versions.BARCODE_SCANNER_VERSION}"
    const val BARCODE_SCANNER_CORE = "com.google.zxing:core:${Versions.BARCODE_SCANNER_CORE_VERSION}"
    const val CIRCLE_PROGRESS_BAR = "com.dinuscxj:circleprogressbar:${Versions.CIRCLE_PROGRESS_BAR_VERSION}"
    const val SHIMMER = "com.facebook.shimmer:shimmer:${Versions.SHIMMER_VERSION}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON_VERSION}"

}

object Testing {
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT_VERSION}"
    const val ANDROIDX_TEST_EXT_JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.ANDROIDX_TEST_EXT_VERSION}"
    const val MOCKITO = "org.mockito:mockito-core:${Versions.MOCKITO_VERSION}"
    const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO_KOTLIN_VERSION}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.MOCKITO_INLINE_VERSION}"
}