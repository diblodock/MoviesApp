package ru.gpolyunin.moviesapp.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ru.gpolyunin.moviesapp.MainViewModel
import ru.gpolyunin.moviesapp.navigation.Screens
import ru.gpolyunin.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun splashScreen(navController: NavController, viewModel: MainViewModel) {
    var splashAnimation by remember {
        mutableStateOf(false)
    }
    var alphaAnimation = animateFloatAsState(
        targetValue = if (splashAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true) {
        splashAnimation = true
        viewModel.getAllMovies()
        delay(4000)
        navController.navigate(Screens.Main.route)
    }
    splash(alpha = alphaAnimation.value)
}

@Composable
fun splash(alpha: Float) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "splash",
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun prevSplash() {
    MoviesAppTheme {
        splash(1f)
    }
}