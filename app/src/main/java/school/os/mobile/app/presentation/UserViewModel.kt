package school.os.mobile.app.presentation


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import school.os.mobile.app.domain.use_case.GetUserUseCase
import school.os.mobile.app.utils.Constants
import school.os.mobile.app.utils.DataParser
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    init {
        savedStateHandle.get<String>(Constants.ROUTE_PARAM_USER_ID)?.let {userId ->
            loadUser(userId)
        }
    }

    // getting cryptocurrencies list using
    // repository and passing it into live data
    private fun loadUser(userId: String) {
        userUseCase(userId).onEach { result ->
            when (result) {
                is DataParser.Loading -> {
                    _state.value = UserState(isLoading = true)
                }
                is DataParser.Error -> {
                    _state.value = UserState(error = result.message ?: "")
                }
                is DataParser.Success -> {
                    _state.value = UserState(data = result.data)
                }
            }

        }.launchIn(viewModelScope)
    }
}