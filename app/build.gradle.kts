import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas") // If Using Ksp
}

base {
    // Naming APK // AAB (stable name avoids invalidating incremental tasks every minute)
    archivesName = "${ProjectSetting.NAME_APK}-${ProjectSetting.PROJECT_VERSION_CODE}-[${ProjectSetting.PROJECT_VERSION_NAME}]"
}

android {

    namespace = ProjectSetting.PROJECT_NAME_SPACE_APP
    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK

    defaultConfig {
        applicationId = ProjectSetting.PROJECT_APP_ID
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        targetSdk = ProjectSetting.PROJECT_TARGET_SDK
        versionCode = ProjectSetting.PROJECT_VERSION_CODE
        versionName = ProjectSetting.PROJECT_VERSION_NAME

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Declaration build config
        buildConfigField("String", "DATABASE_NAME", ProjectSetting.DB)
        buildConfigField("String", "PREF_NAME", ProjectSetting.PREF)

        // Declaration apps name debug mode
        val debugAttribute = "Dev"
        val nameAppDebug = "${ProjectSetting.NAME_APP} $debugAttribute"

        // Inject app name for debug
        resValue("string", "app_name", nameAppDebug)
        resValue("string", "app_name_short", "${ProjectSetting.NAME_APP_SHORT} $debugAttribute")

        // Inject admob id for debug
        resValue("string", "admob_app_id", AdmobValue.Debug.ADMOB_APP_ID)
        resValue("string", "admob_interstitial", AdmobValue.Debug.ADMOB_INTERSTITIAL)

    }

    buildTypes {

        getByName("debug") {
            applicationIdSuffix = ".dev"
        }

        getByName("release") {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Release uses the debug key (no secrets in repo). Swap in a real key via CI secrets before Play Store.
            signingConfig = signingConfigs.getByName("debug")

            // Inject app name for release
            resValue("string", "app_name", ProjectSetting.NAME_APP)
            resValue("string", "app_name_short", ProjectSetting.NAME_APP_SHORT)


            // Inject admob id for release
            resValue("string", "admob_app_id", AdmobValue.ADMOB_APP_ID)
            resValue("string", "admob_interstitial", AdmobValue.ADMOB_INTERSTITIAL)

        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
        resValues = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(project(DependencyGradle.MODULE_LIB_KEYBOARD))

    implementation(libs.androidx.work.ktx)
    implementation(libs.google.hilt)

    // Room & Coroutines
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    ksp(libs.google.hilt.compiler)
    ksp(libs.androidx.room.compiler)
    ksp(libs.androidx.lifecycle.compiler)

    androidTestImplementation(composeBom)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.json)
    debugImplementation(libs.androidx.compose.ui.tooling)

}