package school.os.mobile.app.ui.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import school.os.mobile.app.R
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.AppPrimaryOutlineButton
import school.os.mobile.app.ui.theme.SchoolOnboardTheme
import school.os.mobile.app.ui.theme.Typography

class RegisterOrLoginPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolOnboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

//MARK: the main content area
@Composable
fun MainContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            text = stringResource(id = R.string.welcome_title),
            textAlign = TextAlign.Center,
            style = Typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string.welcome_message),
            textAlign = TextAlign.Center,
            style = Typography.bodySmall
        )
        AppPrimaryButton(title = stringResource(id = R.string.login_title), click = {

        })
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(text = "Or", color = Color.Gray)
            Divider(color = Color.Gray, thickness = 1.dp)
        }
        AppPrimaryOutlineButton(click = { }, title = stringResource(id = R.string.register_title))
        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SchoolOnboardTheme {
        MainContent()
    }
}