package ru.gpolyunin.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.gpolyunin.moviesapp.MainViewModel
import ru.gpolyunin.moviesapp.screens.DetailsScreen
import ru.gpolyunin.moviesapp.screens.mainScreen
import ru.gpolyunin.moviesapp.screens.splashScreen
import ru.gpolyunin.moviesapp.utils.Constants

sealed class Screens(val route: String) {
    object Splash : Screens(route = Constants.Screens.SPLASH_SCREEN)
    object Main : Screens(route = Constants.Screens.MAIN_SCREEN)
    object Details : Screens(route = Constants.Screens.DETAILS_SCREEN)
}

@Composable
fun setupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            splashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Main.route) {
            mainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Details.route + "/{Id}") { backStackEntry ->
            DetailsScreen(navController = navController, viewModel = viewModel, itemId = backStackEntry.arguments?.getString("Id")?: "1")
        }
    }
}