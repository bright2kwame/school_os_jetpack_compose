package school.os.mobile.app.utils

sealed class ScreenAndRoute(val route: String) {
    object RegisterLogin : ScreenAndRoute(route = "register_login_screen")
    object OnboardScreen : ScreenAndRoute(route = "onboard_screen")
    object PhoneNumberScreen : ScreenAndRoute(route = "phone_number_screen")
    object ResetPasswordScreen : ScreenAndRoute(route = "reset_password_screen")
    object LoginPasswordScreen : ScreenAndRoute(route = "login_password_screen")
    object VerifyPhoneNumberScreen : ScreenAndRoute(route = "verify_phone_number_screen")
    object CreatePasswordScreen : ScreenAndRoute(route = "create_password_screen")
    object HomeMoreScreen : ScreenAndRoute(route = "home_more_screen")
    object HomeAnalyticsScreen : ScreenAndRoute(route = "home_analytics_screen")
    object HomePaymentsScreen : ScreenAndRoute(route = "home_payments_screen")
    object HomeScreen : ScreenAndRoute(route = "home_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}