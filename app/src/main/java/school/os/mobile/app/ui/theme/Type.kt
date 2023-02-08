package school.os.mobile.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import school.os.mobile.app.R

//creating a font theme for the application
val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_black, FontWeight.W500),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_light, FontWeight.ExtraLight),
    Font(R.font.roboto_medium, FontWeight.SemiBold),
    Font(R.font.roboto_italic, FontWeight.Thin, FontStyle.Italic),
)

//create a default style for the rest of the styles
// to inherit
val defaultTextStyle = TextStyle(
    fontFamily = Roboto,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = defaultTextStyle.copy(
        fontSize = 56.sp,
        lineHeight = 56.sp,
        letterSpacing = (-0.25).sp
    ),
    bodyLarge = defaultTextStyle.copy(
        fontSize = 32.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = defaultTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = defaultTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = defaultTextStyle.copy(
        fontSize = 20.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = defaultTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    )
)