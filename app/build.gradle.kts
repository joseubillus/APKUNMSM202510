plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.apkunmsm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.apkunmsm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /*ndk {
            //noinspection ChromeOsAbiSupport
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }*/
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.android.async.http)
    //Implementar Grafico Lineal
    //implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation(libs.mpandroidchart)
    //Implementar Tensorflow-Lite
    implementation("org.tensorflow:tensorflow-lite:2.13.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.3.1")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.13.0")
}