package school.os.mobile.app.ui.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import school.os.mobile.app.R
import school.os.mobile.app.model.OnboardPageItem
import school.os.mobile.app.model.onboardPages
import school.os.mobile.app.ui.theme.*
import school.os.mobile.app.utils.ScreenAndRoute

class OnboardPage : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolOnboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pager = rememberPagerState(initialPage = 0)
                    PagerUI(
                        items = onboardPages,
                        pagerState = pager,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PagerUI(
        items = onboardPages,
        pagerState = rememberPagerState(initialPage = 0),
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerUI(items: List<OnboardPageItem>, pagerState: PagerState, modifier: Modifier) {
    val navController = rememberNavController()
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(pageCount = items.size, state = pagerState) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(items[pagerState.currentPage].color),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        contentScale = ContentScale.Inside,
                        painter = painterResource(id = items[it].image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp)
                    )
                }
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                shape = OnboardBottomCardShapes.large
            ) {
                Box(modifier = Modifier.background(Color.White)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        PagerIndicator(items, pagerState.currentPage)
                        Text(
                            text = items[pagerState.currentPage].title,
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 30.dp, bottom = 10.dp),
                            color = items[pagerState.currentPage].color,
                            style = Typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = items[pagerState.currentPage].description, Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, start = 40.dp, end = 20.dp, bottom = 20.dp),
                            color = Color.Gray,
                            style = Typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 32.dp)
                        ) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = ButtonShapes.small,
                                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                                contentPadding = PaddingValues(16.dp),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 4.5.dp),
                                onClick = {
                                    navController.navigate(ScreenAndRoute.MainScreen.route)
                                }) {
                                    Text(text = stringResource(id = R.string.get_started), textAlign = TextAlign.Center, style = Typography.titleMedium)

                            }
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun PagerIndicator(items: List<OnboardPageItem>, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            IndicatorPage(it == currentPage, items[currentPage].color)
        }
    }
}

@Composable
fun IndicatorPage(isSelector: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelector) 40.dp else 10.dp)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelector) color else Color.Gray.copy(alpha = 0.5f))
    )
}





