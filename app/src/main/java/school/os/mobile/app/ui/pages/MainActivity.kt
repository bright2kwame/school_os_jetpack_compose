package school.os.mobile.app.ui.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import school.os.mobile.app.domain.model.onboardPages
import school.os.mobile.app.ui.theme.*
import school.os.mobile.app.utils.ScreenAndRoute

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolOnboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val pager = rememberPagerState(initialPage = 0)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenAndRoute.OnboardScreen.route
                    ) {
                        composable(route = ScreenAndRoute.OnboardScreen.route) {
                            OnboardPagerScreen(
                                items = onboardPages,
                                pagerState = pager,
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                        composable(route = ScreenAndRoute.RegisterLogin.route) {
                            RegisterOrLoginScreen(navController = navController)
                        }
                        composable(route = ScreenAndRoute.PhoneNumberScreen.route) {
                            PhoneNumberScreen(navController = navController)
                        }
                    }

                }
            }
        }
    }
}
