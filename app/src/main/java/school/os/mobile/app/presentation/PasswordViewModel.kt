package school.os.mobile.app.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.use_case.GetPasswordUseCase
import school.os.mobile.app.domain.use_case.GetUserLoginUseCase
import school.os.mobile.app.utils.Constants
import school.os.mobile.app.utils.DataParser
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val useCase: GetPasswordUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SimpleResultState())
    val state: State<SimpleResultState> = _state


    fun initPasswordReset(phone: String) {
        useCase.initPasswordReset(phone).onEach {
            handleResponse(it)
        }
    }

    // repository and passing it into live data
    fun resetPassword(phone: String, code: String, password: String) {
        useCase.resetPassword(phone = phone, code = code, password = password).onEach { result ->
            handleResponse(result = result)
        }.launchIn(viewModelScope)
    }

    private fun handleResponse(result: DataParser<SimpleResult>) {
        when (result) {
            is DataParser.Loading -> {
                _state.value = SimpleResultState(isLoading = true, hasError = false)
            }
            is DataParser.Error -> {
                _state.value = SimpleResultState(
                    error = result.message ?: ViewModelConstants.SOMETHING_FAILED,
                    hasError = true
                )
            }
            is DataParser.Success -> {
                _state.value = SimpleResultState(data = result.data, hasError = false)
            }
        }
    }
}