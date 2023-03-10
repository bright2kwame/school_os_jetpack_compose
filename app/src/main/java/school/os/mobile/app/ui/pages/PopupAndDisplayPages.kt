package school.os.mobile.app.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import school.os.mobile.app.R
import school.os.mobile.app.ui.theme.Background
import school.os.mobile.app.ui.theme.Primary
import school.os.mobile.app.ui.theme.Shapes
import school.os.mobile.app.ui.theme.Typography

@Composable
fun CustomAlertDialog(
    title: String,
    message: String,
    negativeAction: String,
    positiveAction: String,
    onDismiss: (Boolean) -> Unit,
    setActionTaken: (Boolean) -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss(false) }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = Shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_true_friends),
                    contentDescription = title,
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Inside
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(),
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = message,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = Typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(Background),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(
                        onClick = {
                            setActionTaken(true)
                        },
                        Modifier.padding(top = 5.dp, bottom = 5.dp),
                    ) {
                        Text(
                            text = positiveAction,
                            fontWeight = FontWeight.ExtraBold,
                            color = Primary,
                            style = Typography.titleMedium,
                        )
                    }
                    TextButton(
                        onClick = {
                            setActionTaken(false)
                        },
                        Modifier.padding(top = 5.dp, bottom = 5.dp),
                    ) {
                        Text(
                            text = negativeAction,
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            style = Typography.titleMedium,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingView(progressMessage: String, onDismiss: (Boolean) -> Unit) {
    Dialog(onDismissRequest = { onDismiss(false) }) {
        Card(
            shape = Shapes.small,
            modifier = Modifier,
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(0.dp)
            ) {
                Text(
                    text = progressMessage,
                    Modifier
                        .padding(8.dp), textAlign = TextAlign.Center
                )
                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewDialog() {
    CustomAlertDialog(
        title = "School OS",
        message = "Showing a sample message",
        "Cancel",
        positiveAction = "Done",
        {},
        {}
    )
}

@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DefaultPreviewProgress() {
    LoadingView(
        progressMessage = "Showing a sample message",
        onDismiss = {}
    )
}