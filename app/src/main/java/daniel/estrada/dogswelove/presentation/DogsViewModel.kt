package daniel.estrada.dogswelove.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.estrada.dogswelove.domain.model.Dog
import daniel.estrada.dogswelove.domain.usecases.FetchDogsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val fetchDogsUseCase: FetchDogsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            try {
                val dogs = fetchDogsUseCase()
                _uiState.value = _uiState.value.copy(dogs = dogs)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val dogs: List<Dog> = emptyList(),
    val error: String? = null
)