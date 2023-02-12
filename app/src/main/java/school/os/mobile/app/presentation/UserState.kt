package school.os.mobile.app.presentation

import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.model.UserResult

data class UserState(
    val isLoading: Boolean = false,
    var hasError: Boolean = false,
    val data: UserResult? = null,
    val error: String = ""
)

data class SimpleResultState(
    val isLoading: Boolean = false,
    var hasError: Boolean = false,
    val data: SimpleResult? = null,
    val error: String = ""
)