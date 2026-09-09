package com.example.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.screens.CategoryScreen
import com.example.ui.screens.ColoringCanvasScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.SavedDrawingsScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SplashScreen

@Composable
fun KidsAppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.SPLASH,
        enterTransition = {
            fadeIn(animationSpec = tween(250))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(200))
        }
    ) {
        // Splash Screen
        composable(NavRoutes.SPLASH) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable(NavRoutes.HOME) {
            HomeScreen(
                onCategoryClick = { categoryId ->
                    navController.navigate(NavRoutes.category(categoryId))
                },
                onSavedDrawingsClick = {
                    navController.navigate(NavRoutes.SAVED_GALLERY)
                },
                onSettingsClick = {
                    navController.navigate(NavRoutes.SETTINGS)
                }
            )
        }

        // Category Screen (Page Selection)
        composable(
            route = NavRoutes.CATEGORY,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            CategoryScreen(
                categoryId = categoryId,
                onPageClick = { pageId ->
                    navController.navigate(NavRoutes.canvas(pageId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Coloring Canvas Screen
        composable(
            route = NavRoutes.CANVAS,
            arguments = listOf(navArgument("pageId") { type = NavType.StringType })
        ) { backStackEntry ->
            val pageId = backStackEntry.arguments?.getString("pageId") ?: ""
            ColoringCanvasScreen(
                pageId = pageId,
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToGallery = {
                    navController.navigate(NavRoutes.SAVED_GALLERY)
                }
            )
        }

        // Saved Drawings Gallery Screen
        composable(NavRoutes.SAVED_GALLERY) {
            SavedDrawingsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onOpenCanvas = { pageId ->
                    navController.navigate(NavRoutes.canvas(pageId))
                },
                onStartColoringClick = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.HOME) { inclusive = false }
                    }
                }
            )
        }

        // Settings Screen
        composable(NavRoutes.SETTINGS) {
            SettingsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
