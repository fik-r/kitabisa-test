package com.example.kitabisa_assesment.presentation.view.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kitabisa_assesment.R

private const val CONTENT_DESCRIPTION_BACK = "Back"
private const val CONTENT_DESCRIPTION_SEARCH = "Search"
private const val CONTENT_DESCRIPTION_CLEAR = "Clear"

data class AppToolbarParams(
    val title: String = "",
    val onSearchQueryChanged: ((query: String) -> Unit)? = null,
    val onBackClick: (() -> Unit)? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    params: AppToolbarParams
) = with(params) {
    val isSearchMode = remember { mutableStateOf(false) }
    val search = remember { mutableStateOf("") }
    TopAppBar(
        title = {
            if (isSearchMode.value) {
                TextField(
                    value = search.value,
                    onValueChange = {
                        search.value = it
                        onSearchQueryChanged?.invoke(it)
                    },
                    placeholder = { Text(stringResource(R.string.placeholder_search)) },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else {
                Text(title)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        navigationIcon = {
            onBackClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = CONTENT_DESCRIPTION_BACK,
                        tint = Color.Black
                    )
                }
            }
        },
        actions = {
            onSearchQueryChanged?.let {
                IconButton(onClick = {
                    if (!isSearchMode.value) search.value = ""
                    it.invoke("")
                    isSearchMode.value = !isSearchMode.value
                }) {
                    if (isSearchMode.value) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = CONTENT_DESCRIPTION_CLEAR,
                            tint = Color.Black
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = CONTENT_DESCRIPTION_SEARCH,
                            tint = Color.Black
                        )
                    }
                }
            }
        },
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewAppToolbarWithBack() {
    AppToolbar(params = AppToolbarParams(
        title = stringResource(R.string.app_name),
        onBackClick = {}
    ))
}

@Preview(showBackground = false)
@Composable
fun PreviewAppToolbarWithSearch() {
    AppToolbar(
        params = AppToolbarParams(
            title = stringResource(R.string.app_name),
            onSearchQueryChanged = {}
        )
    )
}