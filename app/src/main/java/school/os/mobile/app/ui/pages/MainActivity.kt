package school.os.mobile.app.ui.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import school.os.mobile.app.domain.model.onboardPages
import school.os.mobile.app.ui.pages.onboard.CreatePasswordPage
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

                   //DestinationsNavHost(navGraph = NavGraphs.root)
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
                        //MARK: screen to register or login action
                        composable(route = ScreenAndRoute.RegisterLogin.route) {
                            RegisterOrLoginScreen(navController = navController)
                        }
                        //MARK: screen to enter password
                        composable(route = ScreenAndRoute.PhoneNumberScreen.route) {
                            PhoneNumberScreen(navController = navController)
                        }
                        //MARK: login wit password screen
                        composable( ScreenAndRoute.LoginPasswordScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument("phone") {
                                type = NavType.StringType
                            })) {
                            val phone = requireNotNull(it.arguments).getString("phone")
                            LoginPasswordPage(navController, phone!!)
                        }
                        //MARK: screen to create password
                        composable( ScreenAndRoute.CreatePasswordScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument("phone") {
                                type = NavType.StringType
                            })) {
                            val phone = requireNotNull(it.arguments).getString("phone")
                            CreatePasswordPage(navController, phone!!)
                        }
                        //MARK: screen to verify phone number
                        composable( ScreenAndRoute.VerifyPhoneNumberScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument("phone") {
                                type = NavType.StringType
                            })) {
                            val phone = requireNotNull(it.arguments).getString("phone")
                            VerificationPage(navController, phone!!)
                        }
                        //MARK: screen to reset the password
                        composable( ScreenAndRoute.ResetPasswordScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument("phone") {
                                type = NavType.StringType
                            })) {
                            val phone = requireNotNull(it.arguments).getString("phone")
                            ResetPasswordPage(navController, phone!!)
                        }

                    }

                }
            }
        }
    }
}
