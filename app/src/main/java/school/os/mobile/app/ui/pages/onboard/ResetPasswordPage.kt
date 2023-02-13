package school.os.mobile.app.ui.pages.onboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import school.os.mobile.app.R
import school.os.mobile.app.presentation.PasswordViewModel
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.CustomPasswordField
import school.os.mobile.app.ui.pages.CustomAlertDialog
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.utils.ScreenAndRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordPage(
    navController: NavHostController,
    phone: String,
    passwordViewModel: PasswordViewModel
) {
    var uniqueCode by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordAgain by rememberSaveable { mutableStateOf("") }
    val state = passwordViewModel.state
    passwordViewModel.initPasswordReset(phone)
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
                showDialog.value = false
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
        OutlinedTextField(
            value = uniqueCode,
            onValueChange = {
                uniqueCode = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_verification_code),
                    style = Typography.titleMedium
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
        CustomPasswordField(
            stringResource(id = R.string.enter_password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp), password
        ) {
            password = it
        }
        CustomPasswordField(
            stringResource(id = R.string.enter_password_again),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp), passwordAgain
        ) {
            passwordAgain = it
        }
        Box() {
            if (!state.value.isLoading) AppPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp),
                title = stringResource(id = R.string.reset_password),
                click = {
                    if (uniqueCode.isBlank() || password.isBlank() || passwordAgain.isBlank()) {
                        showDialog.value = true
                        return@AppPrimaryButton
                    }
                    if (password != passwordAgain) {
                        showDialog.value = true
                        return@AppPrimaryButton
                    }
                    passwordViewModel.resetPassword(phone, uniqueCode, password)
                }) else CircularProgressIndicator(
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 64.dp),
                color = Primary
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        if (!state.value.isLoading && state.value.hasError) {
            LaunchedEffect(key1 = ScreenAndRoute.ResetPasswordScreen.route) {
                showDialog.value = true
            }
        }
        if (!state.value.isLoading && !state.value.hasError) {
            state.value.data?.let {
                LaunchedEffect(key1 = ScreenAndRoute.LoginPasswordScreen) {
                    navController.navigate(
                        ScreenAndRoute.LoginPasswordScreen.withArgs(phone)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewReset() {
    ResetPasswordPage(
        navController = rememberNavController(),
        phone = "+233500294411",
        passwordViewModel = viewModel()
    )
}