package school.os.mobile.app.ui.pages

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import school.os.mobile.app.R
import school.os.mobile.app.domain.model.OnboardPageItem
import school.os.mobile.app.domain.model.onboardPages
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.theme.*
import school.os.mobile.app.utils.ScreenAndRoute


@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OnboardPagerScreen(
        items = onboardPages,
        pagerState = rememberPagerState(initialPage = 0),
        modifier = Modifier.fillMaxSize(),
        navController = rememberNavController()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardPagerScreen(
    items: List<OnboardPageItem>,
    pagerState: PagerState,
    modifier: Modifier,
    navController: NavHostController
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
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
                        contentDescription = items[pagerState.currentPage].description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight - 350.dp)
                            .padding(all = 32.dp)
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
                                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
                            color = Color.Black,
                            style = Typography.titleLarge,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = items[pagerState.currentPage].description,
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp),
                            color = Color.Gray,
                            style = Typography.titleMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)
                        ) {
                            AppPrimaryButton(
                                modifier = Modifier.fillMaxWidth(),
                                click = {
                                    navController.navigate(ScreenAndRoute.PhoneNumberScreen.route)
                                },
                                title = stringResource(id = R.string.get_started)
                            )
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
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 20.dp)
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





