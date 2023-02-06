package school.os.mobile.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val OnboardBottomCardShapes = Shapes(
    large = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
    small = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    medium = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
)

val ButtonShapes = Shapes(
    large = RoundedCornerShape(percent = 50),
    small = RoundedCornerShape(percent = 10),
    medium = RoundedCornerShape(percent = 25),
)