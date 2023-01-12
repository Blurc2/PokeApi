package com.gshp.desktop

import android.app.Application
import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.cgsa.artifacts.Constants
import com.cgsa.artifacts.R
import com.cgsa.artifacts.data.repository.preferences.AppPreferenceRepository
import com.cgsa.artifacts.domain.repository.PreferenceRepository
import com.facebook.stetho.Stetho
import com.gshp.desktop.framework.ServiceLocator

private const val BASE_URL = "https://inbevservicios.gshp-apps.com/"
private const val PREFERENCE_NAME = "DesktopPreferences"

class DesktopApplication : Application() {

    companion object {
        val serviceLocator by lazy {
            ServiceLocator(BASE_URL)
        }
        lateinit var appPreferenceRepository: PreferenceRepository
    }

    override fun onCreate() {
        super.onCreate()

        Constants.SERVICES.BASIC_AUTH_USERNAME = getString(R.string.S3_BASIC_USERNAME)
        Constants.SERVICES.BASIC_AUTH_PASSWORD = getString(R.string.S3_BASIC_PASSWORD)
        Constants.SERVICES.S3_REST_PATH = getString(R.string.S3RestPath)

        appPreferenceRepository = if (BuildConfig.DEBUG || android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M)
            AppPreferenceRepository(applicationContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE))
        else {
            val spec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                .build()

            AppPreferenceRepository(
                EncryptedSharedPreferences.create(
                    applicationContext,
                    PREFERENCE_NAME,
                    MasterKey.Builder(applicationContext).setKeyGenParameterSpec(spec).build(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            )
        }

        if (BuildConfig.DEBUG) {
            val stetho = Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build()
            Stetho.initialize(stetho)
        }
    }
}