plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.anirudha.projectsjetpackcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.anirudha.projectsjetpackcompose"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        compose = true
    }
}

dependencies {

    // Core and Lifecycle libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Activity and Compose dependencies
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Material 3 library for UI components
    implementation(libs.androidx.material3)

    // Navigation for Compose
    implementation(libs.androidx.navigation.compose)

    // Material Icons Extended library for using Camera, Message, etc.
    implementation("androidx.compose.material:material-icons-extended:1.7.4")
    implementation ("androidx.compose.material3:material3:1.3.0")

    // Accompanist Insets for handling insets in Compose
    implementation("com.google.accompanist:accompanist-insets:0.28.0")

    //To enable swipe navigation between screens,integrate a Pager component
    implementation("com.google.accompanist:accompanist-pager:0.36.0") // Check latest version
    implementation("com.google.accompanist:accompanist-pager-indicators:0.36.0")

    implementation("androidx.compose.foundation:foundation:1.7.4")

    // Test Dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debugging tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
