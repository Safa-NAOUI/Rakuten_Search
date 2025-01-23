package com.example.rakuten.bdd.steps

/**
 * Created by Safa NAOUI on 17,January,2025
 */

import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class ProductDetailSteps {

    @When("the user taps on a product from the list")
    fun userTapsOnProduct() {
       // onView(withId(R.id.productList)).perform(click())
    }

    @Then("the user is taken to the product detail screen")
    fun userTakenToProductDetailScreen() {
        /** todo **/
    }

    @Then("the user sees the product title, best price, and description")
    fun userSeesProductDetails() {
        /** todo **/
    }
}
