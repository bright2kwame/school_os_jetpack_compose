package school.os.mobile.app.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import school.os.mobile.app.R
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.CustomPasswordField
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Typography


//MARK: password for the user to login
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPasswordPage(navController: NavHostController) {
    var password by rememberSaveable { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState())
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
            style = Typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 32.dp)
        )
        CustomPasswordField(
            stringResource(id = R.string.enter_password), modifier = Modifier
                .fillMaxWidth()
                .padding(), password
        ) {
            password = it
        }
        AppPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            title = stringResource(id = R.string.login_title),
            click = {

            })
        TextButton(onClick = {}) {
            Text(
                text = stringResource(id = R.string.forgot_password),
                color = Primary,
                style = Typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewLoginPassword() {
    LoginPasswordPage(
        navController = rememberNavController()
    )
}