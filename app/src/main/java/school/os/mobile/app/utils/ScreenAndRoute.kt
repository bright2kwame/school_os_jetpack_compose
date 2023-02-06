package school.os.mobile.app.utils

sealed class ScreenAndRoute(val route:String){
    object MainScreen : ScreenAndRoute(route = "main_screen")
    object OnboardScreen : ScreenAndRoute(route = "onboard_screen")


    fun withArgs(vararg args:String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}