package com.example.rakuten.bdd

import dagger.hilt.android.testing.HiltAndroidTest
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith


/**
 * Created by Safa NAOUI on 17,January,2025
 */
@HiltAndroidTest
@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:src/androidTest/resources/features"],
    glue = ["com.example.rakuten.bdd.steps"],
    plugin = ["pretty"],
    tags = "@SmokeTest" // Optional: Filter scenarios by tags
)
class RunCucumberTest