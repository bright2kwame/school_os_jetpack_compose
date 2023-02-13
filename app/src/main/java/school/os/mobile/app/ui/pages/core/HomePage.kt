package school.os.mobile.app.ui.pages.core

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.White
import school.os.mobile.app.R
import school.os.mobile.app.ui.theme.Typography


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenPage() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        scaffoldState = scaffoldState,
        topBar = { CustomTopBar() },
    ) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun CustomTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name_title),
                style = Typography.titleMedium
            )
        },
        backgroundColor = White,
        contentColor = Color.Gray,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Outlined.Notifications, contentDescription = "")
            }
        }
    )
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Analytics,
        BottomNavItem.Payments,
        BottomNavItem.More,
    )
    BottomNavigation(
        backgroundColor = White,
        contentColor = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = Primary,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Analytics.screenRoute) {
            AnalyticsScreen()
        }
        composable(BottomNavItem.Payments.screenRoute) {
            PaymentsScreen()
        }
        composable(BottomNavItem.More.screenRoute) {
            MoreScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    HomeScreenPage()
}