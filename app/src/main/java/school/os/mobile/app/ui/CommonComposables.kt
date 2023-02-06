package school.os.mobile.app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import school.os.mobile.app.R
import school.os.mobile.app.ui.theme.ButtonShapes
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Typography
import school.os.mobile.app.utils.ScreenAndRoute

//MARK: all primary buttons used in the application
@Composable
fun AppPrimaryButton(click: () -> Unit, title: String) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        shape = ButtonShapes.small,
        colors = ButtonDefaults.buttonColors(containerColor = Primary),
        contentPadding = PaddingValues(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 4.5.dp),
        onClick = {
           click()
        }) {
        Text(text = title, textAlign = TextAlign.Center, style = Typography.titleMedium)

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
        colors = ButtonDefaults.buttonColors(containerColor = Primary),
        contentPadding = PaddingValues(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 4.5.dp),
        onClick = {
            click()
        }) {
        Text(text = title, textAlign = TextAlign.Center, style = Typography.titleMedium)

    }
}