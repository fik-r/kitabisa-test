plugins {
    id("com.google.devtools.ksp")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.kitabisa_assesment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kitabisa_assesment"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"http://universities.hipolabs.com/\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    // Compose and UI
    val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.runtime.livedata)

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Dependency Injection (Hilt)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    androidTestImplementation(libs.hilt.testing)
    androidTestAnnotationProcessor(libs.hilt.compiler)
    testImplementation(libs.hilt.testing)
    testAnnotationProcessor(libs.hilt.compiler)

    // Networking (Retrofit)
    implementation(libs.bundles.retrofit)

    // Database (Room)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Debugging (Chucker)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)

    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}