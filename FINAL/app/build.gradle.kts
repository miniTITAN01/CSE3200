plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.k2024_04_21_livedata_volley"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.k2024_04_21_livedata_volley"
        minSdk = 30
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("com.android.volley:volley:1.2.1")

    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")


    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    implementation ( "androidx.compose.material:material-icons-extended:1.6.6")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.6")
    implementation ("com.google.android.material:material:1.11.0")

    implementation ("io.coil-kt:coil:2.1.0")

    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.privacysandbox.tools:tools-core:1.0.0-alpha08")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}