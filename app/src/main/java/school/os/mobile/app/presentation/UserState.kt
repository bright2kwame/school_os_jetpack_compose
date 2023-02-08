package school.os.mobile.app.presentation

import school.os.mobile.app.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val data: User? = null,
    val error: String = ""
)