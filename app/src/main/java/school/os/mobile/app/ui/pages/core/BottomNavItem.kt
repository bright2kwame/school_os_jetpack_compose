package school.os.mobile.app.ui.pages.core

import school.os.mobile.app.R
import school.os.mobile.app.utils.ScreenAndRoute


sealed class BottomNavItem(var title: String, var icon: Int, var screenRoute: String) {
    object Home : BottomNavItem(
        "Home",
        R.drawable.ic_home, ScreenAndRoute.HomeScreen.route
    )

    object Analytics : BottomNavItem(
        "Analytics",
        R.drawable.ic_analytics, ScreenAndRoute.HomeAnalyticsScreen.route
    )

    object Payments : BottomNavItem(
        "Payments",
        R.drawable.ic_payments, ScreenAndRoute.HomePaymentsScreen.route
    )

    object More : BottomNavItem(
        "More",
        R.drawable.ic_more, ScreenAndRoute.HomeMoreScreen.route
    )
}
