package school.os.mobile.app.ui.pages.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import school.os.mobile.app.R
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.AppPrimaryOutlineButton
import school.os.mobile.app.ui.theme.SchoolOnboardTheme
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.utils.ScreenAndRoute


//MARK: the main content area
@Composable
fun RegisterOrLoginScreen(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            text = stringResource(id = R.string.welcome_title),
            textAlign = TextAlign.Center,
            style = Typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.welcome_message),
            textAlign = TextAlign.Center,
            style = Typography.titleSmall,
            modifier = Modifier.padding(16.dp)
        )
        AppPrimaryButton(title = stringResource(id = R.string.login_title), click = {
            navController.navigate(ScreenAndRoute.PhoneNumberScreen.route)
        })
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 32.dp)
        ) {
            Divider(
                color = Color.Gray, thickness = 0.5.dp,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Text(
                text = stringResource(id = R.string.register_or_login),
                color = Color.Gray,
                style = Typography.titleSmall,
                modifier = Modifier.background(color = Color.White)
            )
        }
        AppPrimaryOutlineButton(click = {
            navController.navigate(ScreenAndRoute.PhoneNumberScreen.withArgs(""))
        }, title = stringResource(id = R.string.register_title))
        Spacer(modifier = Modifier.weight(1.0f))
    }
}