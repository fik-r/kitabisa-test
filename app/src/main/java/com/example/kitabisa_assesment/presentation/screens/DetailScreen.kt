package com.example.kitabisa_assesment.presentation.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.kitabisa_assesment.presentation.view.layout.AppToolbar
import com.example.kitabisa_assesment.presentation.view.layout.AppToolbarParams

/**
 * @param navController The navigation controller for navigating to previous screen.
 */
@Composable
fun DetailScreen(navController: NavController, url: String) {
    Scaffold(
        topBar = {
            AppToolbar(AppToolbarParams(
                onBackClick = {
                    navController.popBackStack()
                }
            ))
        }
    ) { paddingValues ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            }
        )
    }
}

