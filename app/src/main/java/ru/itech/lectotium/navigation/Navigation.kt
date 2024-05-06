package ru.itech.lectotium.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.itech.lectotium.ui.app.screen.LoginScreen
import ru.itech.lectotium.ui.app.screen.RegisterScreen
import ru.itech.lectotium.ui.app.screen.VideoPlayerScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                navigateToRegister = { navController.navigate("register") },
                navigateToVideoPlayer = { navController.navigate("videoPlayer") }
            )
        }
        composable("register") {
            RegisterScreen(
                navigateToLogin = { navController.popBackStack() }
            )
        }
        composable("videoPlayer") {
            VideoPlayerScreen()
        }
    }
}