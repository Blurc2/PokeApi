plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

private object Plugins {
    const val ANDROID_GRADLE = "7.2.2"
    const val KOTLIN = "1.6.10"
    const val NAV = "2.5.3"
}

dependencies {
    implementation("com.android.tools.build:gradle:${Plugins.ANDROID_GRADLE}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Plugins.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${Plugins.NAV}")
}