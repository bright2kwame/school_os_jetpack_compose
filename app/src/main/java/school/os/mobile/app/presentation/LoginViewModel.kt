package school.os.mobile.app.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import school.os.mobile.app.domain.use_case.GetUserLoginUseCase
import school.os.mobile.app.utils.Constants
import school.os.mobile.app.utils.DataParser
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: GetUserLoginUseCase
) : ViewModel() {
    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    // repository and passing it into live data
    fun login(phone: String, password: String) {
        useCase(phone = phone, password = password).onEach { result ->
            when (result) {
                is DataParser.Loading -> {
                    _state.value = UserState(isLoading = true, hasError = false)
                }
                is DataParser.Error -> {
                    _state.value =
                        UserState(
                            error = result.message ?: ViewModelConstants.SOMETHING_FAILED,
                            hasError = true
                        )
                }
                is DataParser.Success -> {
                    _state.value = UserState(data = result.data, hasError = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}