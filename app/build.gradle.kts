plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.sonarqube)
    id("kotlin-kapt")
}
hilt {
    enableAggregatingTask = false
}
android {
    namespace = "com.example.rakuten"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rakuten"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    flavorDimensions += "env"

    productFlavors {
        create("dev") {
            dimension = "env"
            versionName = "1.0.0-dev"
            buildConfigField(
                "String",
                "API_URL",
                "\"${project.findProperty("API_URL_${name.uppercase()}") ?: "https://default.dev.url"}\""
            )
        }

        create("staging") {
            dimension = "env"
            versionName = "1.0.0-staging"
            buildConfigField(
                "String",
                "API_URL",
                "\"${project.findProperty("API_URL_${name.uppercase()}") ?: "https://default.staging.url"}\""
            )
        }

        create("prod") {
            dimension = "env"
            versionName = "1.0.0"
            buildConfigField(
                "String",
                "API_URL",
                "\"${project.findProperty("API_URL_${name.uppercase()}") ?: "https://default.prod.url"}\""
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        languageVersion = "1.9"
        apiVersion = "1.9"
    }

    packaging {
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}


dependencies {

    /** Project Modules **/
    implementation(project(":domain"))
    implementation(project(":data"))

    /** Core AndroidX Libraries **/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    /** Jetpack Compose **/
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    /** Kotlin Standard Library **/
    implementation(libs.kotlin.stdlib)

    /** Hilt DI **/
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler) // Correctly use kapt for Hilt compiler
    implementation(libs.androidx.hilt.navigation.compose)

    /** Retrofit & Gson **/
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.navigation.compose)

    /** Firebase **/
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    /** Testing **/
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /** Cucumber Testing **/
    androidTestImplementation(libs.cucumber.java)
    androidTestImplementation(libs.cucumber.junit)

    /** coroutine **/
    implementation(libs.coroutines.kotlinx)
    implementation(libs.kotlinx.coroutines.core)

    /** Other Dependencies **/
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.core.testing)

    /** AndroidX Fragment **/
    implementation(libs.fragment.ktx)
    implementation(libs.javapoet) // Use 1.13.0 to avoid Hilt issues

    /** Room **/
    implementation(libs.androidx.room.runtime.v250)
    kapt(libs.androidx.room.compiler.v250)
    implementation(libs.androidx.room.ktx.v250)
    
}
