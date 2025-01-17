plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    //#alias(libs.plugins.sonarqube)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.akuten"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rakuten"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        buildConfigField(
//            "String",
//            "API_URL_DEV",
//            "\"${project.findProperty("API_URL_DEV") ?: "https://default.dev.url"}\""
//        )
//        buildConfigField(
//            "String",
//            "API_URL_STAGING",
//            "\"${project.findProperty("API_URL_STAGING") ?: "https://default.staging.url"}\""
//        )
//        buildConfigField(
//            "String",
//            "API_URL_PROD",
//            "\"${project.findProperty("API_URL_PROD") ?: "https://default.prod.url"}\""
//        )
    }
//    buildFeatures {
//        buildConfig = true
//        compose = true
//    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
//    flavorDimensions+= "env"
//
//    productFlavors {
//        create("dev") {
//            dimension = "env"
//            versionName = "1.0.0-dev"
//            buildConfigField("String", "API_URL", "\"${project.findProperty("API_URL_DEV") ?: "https://default.dev.url"}\"")
//        }
//
//        create("staging") {
//            dimension = "env"
//            versionName = "1.0.0-staging"
//            buildConfigField("String", "API_URL", "\"${project.findProperty("API_URL_STAGING") ?: "https://default.staging.url"}\"")
//        }
//
//        create("prod") {
//            dimension = "env"
//            versionName = "1.0.0"
//            buildConfigField("String", "API_URL", "\"${project.findProperty("API_URL_PROD") ?: "https://default.prod.url"}\"")
//        }
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        languageVersion = "1.9"
        apiVersion = "1.9"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }
//    sonar {
//        properties {
//            property("sonar.projectKey", "Safa-NAOUI_Rakuten_Search")
//            property("sonar.organization", "safa-naoui")
//            property("sonar.host.url", "https://sonarcloud.io")
//        }
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.kotlin.stdlib)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    /** jetPack compoise **/
    implementation(libs.compose.ui)
    implementation(libs.compose.material)

    /** Hilt DI **/
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)

    /** Retrofit & Gson **/
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.navigation.compose)

    /** Firebase **/
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /** Additional dependencies **/
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}