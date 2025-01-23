package com.example.rakuten.app.ui.components

import android.text.Html
import android.text.Spanned
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.withStyle

@Composable
fun DisplayCustomHtml(productDescription: String) {
    // Convert HTML to Spanned (raw text)
    val htmlDescription: Spanned = Html.fromHtml(productDescription, Html.FROM_HTML_MODE_LEGACY)

    // Convert the Spanned to AnnotatedString for Compose, manually handle links
    val annotatedString = buildAnnotatedString {
        val regex = """<a href="(.*?)">(.*?)</a>""".toRegex()
        var lastEndIndex = 0

        // Iterate over all links
        regex.findAll(htmlDescription.toString()).forEach { matchResult ->
            val url = matchResult.groupValues[1]
            val linkText = matchResult.groupValues[2]

            // Append any text before the link
            append(htmlDescription.toString().substring(lastEndIndex, matchResult.range.first))

            // Add clickable link
            pushStringAnnotation(tag = "URL", annotation = url)
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append(linkText)
            }
            pop()

            lastEndIndex = matchResult.range.last + 1
        }

        // Append any remaining text after the last link
        append(htmlDescription.toString().substring(lastEndIndex))
    }

    // Display the annotated string with clickable links
    Column {
        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        // Handle the link click (e.g., navigate to a URL)
                        // For example, print the URL or perform navigation
                        println("Clicked URL: ${annotation.item}")
                    }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductDescription() {
    val productDescription = """
        <li class="sub"><span class="label">Fabricant&nbsp;: </span><em class="value"><a class="ft_link" href="https://www.samsung.com">Samsung</a></em></li>
        <li class="sub"><span class="label">Référence fabricant&nbsp;: </span><em class="value">SM-G991BZVDEUB - SM-G991BZVDEUH</em></li>
        <li class="sub"><span class="label">Poids&nbsp;: </span><em class="value">169</em></li>
        <li class="sub"><span class="label">Interface sans fil&nbsp;: </span><em class="value">NFC</em></li>
        <p>Produit soumis à la Rémunération Pour Copie Privée.&nbsp;<a href="https://www.example.com">En savoir plus</a></p>
        <p><a href="https://www.example.com">Voir le montant de la Rémunération Pour Copie Privée</a></p>
    """
    DisplayCustomHtml(productDescription)
}
