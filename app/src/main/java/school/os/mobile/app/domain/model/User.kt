package school.os.mobile.app.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val firstName: String,
    val id: String,
    val lastName: String,
    val phoneNumber: String
)