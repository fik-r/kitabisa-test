package com.example.kitabisa_assesment.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kitabisa_assesment.presentation.screens.DetailScreen
import com.example.kitabisa_assesment.presentation.screens.HomeScreen
import com.example.kitabisa_assesment.utils.Constant.DETAIL_SCREEN
import com.example.kitabisa_assesment.utils.Constant.HOME_SCREEN
import com.example.kitabisa_assesment.utils.Constant.PATH_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0
                )
            }
        }
        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = HOME_SCREEN
    ) {
        composable(HOME_SCREEN) {
            HomeScreen(navController, hiltViewModel())
        }
        composable("${DETAIL_SCREEN}/{${PATH_URL}}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString(PATH_URL).orEmpty()
            DetailScreen(navController, Uri.decode(url))
        }
    }
}