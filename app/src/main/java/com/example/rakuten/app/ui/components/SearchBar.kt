package com.example.rakuten.app.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onClear: () -> Unit,
    label: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { newQuery ->
            onSearchQueryChanged(newQuery)
            isError = newQuery.isEmpty() // Vérifier si le champ est vide
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Effacer")
                }
            }
        },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                Log.d("SearchScreen_mainonSearch", "searchQuery="+searchQuery.toString())

                if (searchQuery.isEmpty()) {
                    isError = true
                } else {
                    onSearch()
                    keyboardController?.hide()
                }
            }
        )
    )

    if (isError) {
        Text(
            text = "Le champ ne peut pas être vide",
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}