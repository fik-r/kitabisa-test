package com.example.kitabisa_assesment.presentation.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kitabisa_assesment.R
import com.example.kitabisa_assesment.presentation.view.layout.AppToolbar
import com.example.kitabisa_assesment.presentation.view.layout.AppToolbarParams
import com.example.kitabisa_assesment.presentation.view.university.UniversityList
import com.example.kitabisa_assesment.presentation.view.university.model.University
import com.example.kitabisa_assesment.utils.Constant.DETAIL_SCREEN
import com.example.kitabisa_assesment.utils.UIState

/**
 * @param navController The navigation controller for navigating to the detail screen.
 * @param viewModel The HomeViewModel instance providing the data and logic.
 * @param placeholder A list of placeholder data for preview purposes or static display.
 */
@Composable
fun HomeScreen(
    navController: NavController? = null,
    viewModel: HomeViewModel? = null,
    placeholder: List<University>? = null
) {
    val state = viewModel?.data?.collectAsState()?.value
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            AppToolbar(
                AppToolbarParams(
                    title = stringResource(R.string.app_name),
                    onSearchQueryChanged = { searchQuery.value = it }
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when {
                placeholder != null -> UniversityList(placeholder)
                state is UIState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                state is UIState.Error -> Text(text = state.message)
                state is UIState.Success -> {
                    val filteredData = state.data.filter { university ->
                        university.title.contains(searchQuery.value, ignoreCase = true)
                    }
                    UniversityList(filteredData) {
                        val url = Uri.encode(it.url)
                        navController?.navigate("${DETAIL_SCREEN}/${url}")
                    }
                }
            }
        }
    }
}

/**
 * A Preview composable to display the HomeScreen.
 * This is useful for quickly iterating on the UI without running the app on a device.
 */
@Preview(showBackground = false)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        placeholder = University.getPreviewListData()
    )
}