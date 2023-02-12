package school.os.mobile.app.ui.pages.onboard

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.ui.theme.White


//MARK: verify the phone number added to the field
@Composable
fun VerificationPage(
    navController: NavHostController,
    phoneNumber: String,
    viewModel: VerifyPhoneViewModel
) {
    var code by rememberSaveable { mutableStateOf("") }
    val state = viewModel.state
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
            onOtpTextChange = { text, isValid ->
                code = text
            })
        AppPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
            title = stringResource(id = R.string.verify_number),
            click = {

            })
        Box() {
            if (!state.value.isLoading) TextButton(onClick = {
                viewModel.resendCode(phoneNumber)
            }) {
                Text(
                    text = stringResource(id = R.string.resend_code),
                    color = Primary,
                    style = Typography.titleMedium
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