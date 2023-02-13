package school.os.mobile.app.ui.pages.onboard

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.component.getFullPhoneNumber
import com.togitech.ccp.component.getOnlyPhoneNumber
import com.togitech.ccp.component.isPhoneNumber
import com.togitech.ccp.data.utils.checkPhoneNumber
import com.togitech.ccp.data.utils.getDefaultLangCode
import com.togitech.ccp.data.utils.getDefaultPhoneCode
import com.togitech.ccp.data.utils.getLibCountries
import school.os.mobile.app.R
import school.os.mobile.app.presentation.CheckUserViewModel
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.pages.CustomAlertDialog
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Shapes
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.utils.Constants
import school.os.mobile.app.utils.ResultStatusCode
import school.os.mobile.app.utils.ScreenAndRoute

@Composable
fun PhoneNumberScreen(navController: NavHostController, viewModel: CheckUserViewModel) {
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    val fullPhoneNumber = rememberSaveable { mutableStateOf("") }
    val state = viewModel.state
    val showDialog = remember { mutableStateOf(state.value.hasError) }
    if (showDialog.value) {
        val message =
            if (state.value.hasError) state.value.error else stringResource(id = R.string.phone_number_required)
        CustomAlertDialog(stringResource(id = R.string.app_name),
            message = message,
            stringResource(id = R.string.dialog_action_cancel),
            stringResource(id = R.string.dialog_action_try),
            {
                showDialog.value = it
            },
            {
                showDialog.value = false
            })
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
            text = stringResource(id = R.string.phone_number_message),
            textAlign = TextAlign.Center,
            style = Typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )
        TogiCountryCodePicker(
            onValueChange = {
                phoneNumber.value = it
            },
            focusedBorderColor = Primary,
            unfocusedBorderColor = Color.Gray,
            showCountryFlag = true,
            showCountryCode = true,
            text = phoneNumber.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            shape = Shapes.medium,
            bottomStyle = false
        )
        Box() {
            if (!state.value.isLoading) AppPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 32.dp),
                title = stringResource(id = R.string.login_title),
                click = {
                    fullPhoneNumber.value = getFullPhoneNumber()
                    if (phoneNumber.value.isBlank()) {
                        showDialog.value = true
                        return@AppPrimaryButton
                    }
                    viewModel.checkUser(fullPhoneNumber.value)
                }) else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 64.dp),
                    color = Primary
                )
            }
        }
        Spacer(modifier = Modifier.weight(1.0f))
        if (!state.value.isLoading && state.value.hasError) {
            LaunchedEffect(key1 = ScreenAndRoute.PhoneNumberScreen.route) {
                showDialog.value = true
            }
        }
        if (!state.value.isLoading && !state.value.hasError) {
            state.value.data?.let {
                when (it.responseCode) {
                    ResultStatusCode.NOT_VERIFIED.value.toString() -> {
                        LaunchedEffect(key1 = ScreenAndRoute.VerifyPhoneNumberScreen) {
                            navController.navigate(
                                ScreenAndRoute.VerifyPhoneNumberScreen.withArgs(
                                    fullPhoneNumber.value
                                )
                            )
                        }
                    }
                    ResultStatusCode.NOT_FOUND.value.toString() -> {
                        LaunchedEffect(key1 = ScreenAndRoute.CreatePasswordScreen) {
                            navController.navigate(
                                ScreenAndRoute.CreatePasswordScreen.withArgs(
                                    fullPhoneNumber.value
                                )
                            )
                        }
                    }
                    ResultStatusCode.SUCCESS.value.toString() -> {
                        LaunchedEffect(key1 = ScreenAndRoute.LoginPasswordScreen) {
                            navController.navigate(
                                ScreenAndRoute.LoginPasswordScreen.withArgs(
                                    fullPhoneNumber.value
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}
