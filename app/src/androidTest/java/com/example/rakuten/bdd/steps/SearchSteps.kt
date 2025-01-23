import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.example.rakuten.app.ui.screens.SearchScreen
import com.example.rakuten.app.viewmodel.ProductViewModel
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.junit.Rule
import org.mockito.Mockito.mock


class SearchSteps {

    // Create a rule for Compose tests
    @get:Rule
    val composeTestRule = createComposeRule()


   private val viewModel: ProductViewModel = mock()

    @Given("the user is on the search screen")
    fun userIsOnSearchScreen() {
        composeTestRule.setContent {
            SearchScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }

    @When("the user enters {string} in the search bar")
    fun userEntersKeyword(keyword: String) {
        composeTestRule.onNodeWithTag("search_bar")
            .performTextInput(keyword)
    }

    @When("the user taps the search button")
    fun userTapsSearchButton() {
        composeTestRule.onNodeWithTag("search_button")
            .performClick()
    }

    @Then("the user sees a list of products")
    fun userSeesListOfProducts() {
        composeTestRule.onNodeWithTag("product_list")
            .assertIsDisplayed()
    }
}

