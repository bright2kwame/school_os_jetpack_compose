package school.os.mobile.app.domain.model

import androidx.compose.ui.graphics.Color
import school.os.mobile.app.R
import school.os.mobile.app.ui.theme.ColorBlue
import school.os.mobile.app.ui.theme.ColorGreen
import school.os.mobile.app.ui.theme.ColorYellow

data class OnboardPageItem(
    val title: String,
    val description: String,
    val image:Int, val color: Color
)

//MARK: list of onboard items
val onboardPages = listOf(
    OnboardPageItem(
        "Easy Momo Transfer",
        "Make a quick transaction with someone besides you by scanning their Easy Momo Code",
        R.drawable.ic_back_to_school,
        ColorBlue
    ),
    OnboardPageItem(
        "Easy Select",
        "Pick a contact directly from your contact list and Momo them in less than no time",
        R.drawable.ic_true_friends,
        ColorYellow
    ),
    OnboardPageItem(
        "Secure",
        "We will never know your Momo PIN in anyway.The app does not share your data with any third party. No internet connection required",
        R.drawable.ic_studying_adult,
        ColorGreen
    )
)