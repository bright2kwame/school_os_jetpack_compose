package school.os.mobile.app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import school.os.mobile.app.R
import school.os.mobile.app.ui.theme.ButtonShapes
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.utils.ScreenAndRoute

//MARK: all primary buttons used in the application
@Composable
fun AppPrimaryButton(
    modifier: Modifier = Modifier,
    click: () -> Unit,
    title: String
) {
    Button(
        modifier = modifier,
        shape = ButtonShapes.small,
        colors = ButtonDefaults.buttonColors(containerColor = Primary),
        contentPadding = PaddingValues(16.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 4.5.dp
        ),
        onClick = {
            click()
        }) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = Typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

//MARK: all primary buttons used in the application
@Composable
fun AppPrimaryOutlineButton(click: () -> Unit, title: String) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth(),
        shape = ButtonShapes.small,
        border = BorderStroke(1.dp, Primary),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(16.dp),
        onClick = {
            click()
        }) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = Typography.titleMedium,
            color = Primary,
            fontWeight = FontWeight.SemiBold
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    hintText: String,
    modifier: Modifier,
    password: String,
    passwordChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = {
            passwordChange.invoke(it)
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = {
            Text(
                text = hintText,
                style = Typography.titleMedium
            )
        },
        modifier = modifier,
        trailingIcon = {
            val image = if (passwordVisible)
                R.drawable.ic_remove_eye
            else R.drawable.ic_remove_eye
            val description =
                if (passwordVisible) stringResource(id = R.string.hide_password) else stringResource(
                    id = R.string.show_password
                )
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = ImageVector.vectorResource(id = image), description)
            }
        }
    )
}