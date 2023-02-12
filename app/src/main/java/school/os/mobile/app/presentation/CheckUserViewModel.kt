package school.os.mobile.app.presentation


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import school.os.mobile.app.domain.use_case.GetCheckPhoneNumberUseCase
import school.os.mobile.app.utils.DataParser
import javax.inject.Inject

@HiltViewModel
class CheckUserViewModel @Inject constructor(
    private val userUseCase: GetCheckPhoneNumberUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SimpleResultState())
    val state: State<SimpleResultState> = _state

    // repository and passing it into live data
    fun checkUser(username: String) {
        userUseCase(username).onEach { result ->
            when (result) {
                is DataParser.Loading -> {
                    _state.value = SimpleResultState(isLoading = true, hasError = false)
                }
                is DataParser.Error -> {
                    _state.value = SimpleResultState(error = result.message ?: "Something went wrong", hasError = true)
                }
                is DataParser.Success -> {
                    _state.value = SimpleResultState(data = result.data, hasError = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}