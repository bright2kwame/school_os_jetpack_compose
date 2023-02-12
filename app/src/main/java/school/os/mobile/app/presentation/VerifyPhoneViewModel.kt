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
import school.os.mobile.app.domain.use_case.GetCheckPhoneNumberUseCase
import school.os.mobile.app.domain.use_case.GetVerifyPhoneUseCase
import school.os.mobile.app.utils.Constants
import school.os.mobile.app.utils.DataParser
import javax.inject.Inject

@HiltViewModel
class VerifyPhoneViewModel @Inject constructor(
    private val useUseCase: GetVerifyPhoneUseCase,
    private val checkPhoneUseUseCase: GetCheckPhoneNumberUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(SimpleResultState())
    val state: State<SimpleResultState> = _state

    // repository and passing it into live data
    fun resendCode(phone: String) {
        checkPhoneUseUseCase(phone = phone).onEach { result ->
            handleResponse(result = result)
        }.launchIn(viewModelScope)
    }

    // repository and passing it into live data
    fun verifyPhoneNumber(phone: String, code: String, password: String) {
        useUseCase(phone = phone, code = code, password = password).onEach { result ->
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
                    error = result.message ?: "Something went wrong",
                    hasError = true
                )
            }
            is DataParser.Success -> {
                _state.value = SimpleResultState(data = result.data, hasError = false)
            }
        }
    }
}