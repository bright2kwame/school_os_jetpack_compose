package school.os.mobile.app.ui.pages.onboard

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import school.os.mobile.app.R
import school.os.mobile.app.presentation.VerifyPhoneViewModel
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.pages.CustomAlertDialog
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.ui.theme.White
import school.os.mobile.app.utils.ScreenAndRoute


//MARK: verify the phone number added to the field
@Composable
fun VerificationPage(
    navController: NavHostController,
    phoneNumber: String,
    viewModel: VerifyPhoneViewModel
) {
    var code by rememberSaveable { mutableStateOf("") }
    val state = viewModel.state
    val otpLength: Int = 4
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        val message = if (state.value.hasError) {
            state.value.error
        } else stringResource(id = R.string.verify_message, phoneNumber)
        CustomAlertDialog(stringResource(id = R.string.app_name),
            message,
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
            text = stringResource(id = R.string.verify_message, phoneNumber),
            textAlign = TextAlign.Center,
            style = Typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 32.dp)
        )
        OtpTextField(
            modifier = Modifier.fillMaxSize(),
            otpText = code,
            otpCount = otpLength,
            onOtpTextChange = { text, _ ->
                code = text
            })
        AppPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            title = stringResource(id = R.string.verify_number),
            click = {
                if (code.length < otpLength) {
                    Toast.makeText(
                        context, "Please enter the OTP you received",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    navController.navigate(
                        ScreenAndRoute.CreatePasswordScreen.withArgs(
                            phoneNumber.plus(
                                "@"
                            ).plus(code)
                        )
                    )
                }
            })
        Box() {
            if (!state.value.isLoading) TextButton(onClick = {
                viewModel.resendCode(phoneNumber)
            }) {
                Text(
                    text = stringResource(id = R.string.resend_code),
                    color = Primary,
                    style = Typography.titleMedium,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp),
                )
            }
            else CircularProgressIndicator(
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 32.dp),
                color = Primary
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        if (!state.value.isLoading && state.value.hasError) {
            LaunchedEffect(key1 = ScreenAndRoute.VerifyPhoneNumberScreen.route) {
                showDialog.value = true
            }
        }
        if (!state.value.isLoading && !state.value.hasError && state.value.data != null) {
            LaunchedEffect(key1 = ScreenAndRoute.VerifyPhoneNumberScreen.route) {
                showDialog.value = true
            }
        }
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 4,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it, it.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "•"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(60.dp)
            .height(50.dp)
            .background(color = White)
            .border(
                1.dp, when {
                    isFocused -> Color.DarkGray
                    else -> Color.LightGray
                }, RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = char,
            style = Typography.titleLarge,
            color = if (isFocused) {
                Color.LightGray
            } else {
                Color.DarkGray
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewVerify() {
    VerificationPage(
        navController = rememberNavController(),
        "",
        viewModel = viewModel()
    )
}