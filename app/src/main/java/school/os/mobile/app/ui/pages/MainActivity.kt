package school.os.mobile.app.ui.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import school.os.mobile.app.presentation.CheckUserViewModel
import school.os.mobile.app.presentation.LoginViewModel
import school.os.mobile.app.presentation.PasswordViewModel
import school.os.mobile.app.presentation.VerifyPhoneViewModel
import school.os.mobile.app.ui.pages.core.HomeScreenPage
import school.os.mobile.app.ui.pages.onboard.*
import school.os.mobile.app.ui.theme.*
import school.os.mobile.app.utils.Constants
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
                        //MARK: screen to register or login action
                        composable(route = ScreenAndRoute.RegisterLogin.route) {
                            RegisterOrLoginScreen(navController = navController)
                        }
                        //MARK: screen to enter password
                        composable(route = ScreenAndRoute.PhoneNumberScreen.route) {
                            val vm: CheckUserViewModel by viewModels()
                            PhoneNumberScreen(navController = navController, vm)
                        }
                        //MARK: login wit password screen
                        composable(
                            ScreenAndRoute.LoginPasswordScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument(Constants.ROUTE_PARAM_USER_PHONE) {
                                type = NavType.StringType
                            })
                        ) {
                            requireNotNull(it.arguments).getString(Constants.ROUTE_PARAM_USER_PHONE)
                                ?.let { phone ->
                                    val vm: LoginViewModel by viewModels()
                                    LoginPasswordPage(navController, phone, vm)
                                }
                        }
                        //MARK: screen to create password
                        composable(
                            ScreenAndRoute.CreatePasswordScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument(Constants.ROUTE_PARAM_USER_PHONE) {
                                type = NavType.StringType
                            })
                        ) {
                            val vm: VerifyPhoneViewModel by viewModels()
                            requireNotNull(it.arguments).getString(Constants.ROUTE_PARAM_USER_PHONE)
                                ?.let { phone ->
                                    CreatePasswordPage(navController, phone, vm)
                                }
                        }
                        //MARK: screen to verify phone number
                        composable(
                            ScreenAndRoute.VerifyPhoneNumberScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument(Constants.ROUTE_PARAM_USER_PHONE) {
                                type = NavType.StringType
                            })
                        ) {
                            val phone =
                                requireNotNull(it.arguments).getString(Constants.ROUTE_PARAM_USER_PHONE)
                            phone?.let {
                                val vm: VerifyPhoneViewModel by viewModels()
                                VerificationPage(navController, phone, vm)
                            }
                        }
                        //MARK: screen to reset the password
                        composable(
                            ScreenAndRoute.ResetPasswordScreen.route.plus("/{phone}"),
                            arguments = listOf(navArgument(Constants.ROUTE_PARAM_USER_PHONE) {
                                type = NavType.StringType
                            })
                        ) {
                            requireNotNull(it.arguments).getString(Constants.ROUTE_PARAM_USER_PHONE)
                                ?.let { phone ->
                                    val vm: PasswordViewModel by viewModels()
                                    ResetPasswordPage(navController, phone, vm)
                                }
                        }

                        //MARK: the main tab page
                        composable(route = ScreenAndRoute.MainScreen.route) {
                            HomeScreenPage()
                        }

                    }

                }
            }
        }
    }
}
