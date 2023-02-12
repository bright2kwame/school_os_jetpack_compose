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
    private val useCase: GetUserLoginUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state
    private var _phone: String = ""

    init {
        savedStateHandle.get<String>(Constants.ROUTE_PARAM_USER_PHONE)?.let { phone ->
            _phone = phone
        }
    }

    // repository and passing it into live data
    fun login(password: String) {
        useCase(phone = _phone, password = password).onEach { result ->
            when (result) {
                is DataParser.Loading -> {
                    _state.value = UserState(isLoading = true, hasError = false)
                }
                is DataParser.Error -> {
                    _state.value = UserState(error = result.message ?: "Something went wrong", hasError = true)
                }
                is DataParser.Success -> {
                    _state.value = UserState(data = result.data, hasError = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}