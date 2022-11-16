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