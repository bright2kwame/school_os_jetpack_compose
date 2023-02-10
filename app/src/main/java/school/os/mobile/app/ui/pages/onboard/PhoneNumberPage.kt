package school.os.mobile.app.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.data.utils.checkPhoneNumber
import com.togitech.ccp.data.utils.getDefaultLangCode
import com.togitech.ccp.data.utils.getDefaultPhoneCode
import com.togitech.ccp.data.utils.getLibCountries
import school.os.mobile.app.R
import school.os.mobile.app.ui.AppPrimaryButton
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.SchoolOnboardTheme
import school.os.mobile.app.ui.theme.Typography


@Composable
fun PhoneNumberScreen(navController: NavHostController) {
    val getDefaultLangCode = getDefaultLangCode() // Auto detect language
    val getDefaultPhoneCode = getDefaultPhoneCode() // Auto detect phone code : +90
    var phoneCode by rememberSaveable { mutableStateOf(getDefaultPhoneCode) }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    var defaultLang by rememberSaveable { mutableStateOf(getDefaultLangCode) }
    var verifyText by remember { mutableStateOf("") }
    var isValidPhone by remember { mutableStateOf(true) }

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
                .padding(top = 16.dp, bottom = 16.dp)
        )
        TogiCountryCodePicker(
            onValueChange = {
                phoneNumber.value = it
            },
            pickedCountry = {
                phoneCode = it.countryPhoneCode
                defaultLang = it.countryCode
            },
            defaultCountry = getLibCountries().single { it.countryCode == defaultLang },
            focusedBorderColor = Primary,
            unfocusedBorderColor = Color.Gray,
            showCountryCode = true,
            dialogAppBarTextColor = Color.Black,
            dialogAppBarColor = Color.White,
            error = isValidPhone,
            text = phoneNumber.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        )
        val fullPhoneNumber = "$phoneCode${phoneNumber.value}"
        val checkPhoneNumber = checkPhoneNumber(
            phone = phoneNumber.value,
            fullPhoneNumber = fullPhoneNumber,
            countryCode = defaultLang
        )
        AppPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp),
            title = stringResource(id = R.string.login_title),
            click = {
                verifyText = if (checkPhoneNumber) {
                    isValidPhone = true
                    "Phone Number Correct"
                } else {
                    isValidPhone = false
                    "Phone Number is Wrong"
                }
                if (isValidPhone) {
                    Log.e("PHONE", phoneNumber.value)
                }
            })
        Spacer(modifier = Modifier.weight(1.0f))
    }

}
