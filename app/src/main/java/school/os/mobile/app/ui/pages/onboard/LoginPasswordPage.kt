package school.os.mobile.app.ui.pages.onboard

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.annotation.Destination
import school.os.mobile.app.R
import school.os.mobile.app.presentation.LoginViewModel
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.CustomPasswordField
import school.os.mobile.app.ui.pages.CustomAlertDialog
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.utils.ScreenAndRoute


//MARK: password for the user to login
@Destination
@Composable
fun LoginPasswordPage(
    navController: NavHostController,
    phoneNumber: String,
    loginViewModel: LoginViewModel
) {
    var password by rememberSaveable { mutableStateOf("") }
    val state = loginViewModel.state
    val context = LocalContext.current

    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CustomAlertDialog(stringResource(id = R.string.app_name),
            stringResource(id = R.string.error_password_not_matching),
            stringResource(id = R.string.dialog_action_cancel),
            stringResource(id = R.string.dialog_action_done),
            onDismiss = {
                showDialog.value = it
            },
            setActionTaken = {

            }
        )
    }

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
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            style = Typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(id = R.string.password_title_hint, phoneNumber),
            textAlign = TextAlign.Center,
            style = Typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 32.dp),

            )
        CustomPasswordField(
            stringResource(id = R.string.enter_password), modifier = Modifier
                .fillMaxWidth()
                .padding(), password
        ) {
            password = it
        }
        if (!state.value.isLoading) AppPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            title = stringResource(id = R.string.login_title),
            click = {
                if (password.isBlank()) {
                    showDialog.value = true
                } else {
                    loginViewModel.login(phoneNumber, password)
                }
            }) else CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .padding(start = 16.dp, end = 16.dp, top = 32.dp),
            color = Primary
        )
        TextButton(onClick = {
            navController.navigate(ScreenAndRoute.ResetPasswordScreen.withArgs(phoneNumber))
        }) {
            Text(
                text = stringResource(id = R.string.forgot_password),
                color = Primary,
                style = Typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        if (!state.value.isLoading && !state.value.hasError) {
            state.value.data?.let {
                LaunchedEffect(key1 = ScreenAndRoute.MainScreen) {
                    navController.navigate(
                        ScreenAndRoute.MainScreen.route
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewLoginPassword() {
    LoginPasswordPage(
        navController = rememberNavController(),
        phoneNumber = "+233500294411",
        loginViewModel = viewModel()
    )
}