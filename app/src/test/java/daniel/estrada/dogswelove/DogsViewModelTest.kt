package daniel.estrada.dogswelove

import app.cash.turbine.test
import daniel.estrada.dogswelove.domain.model.Dog
import daniel.estrada.dogswelove.domain.usecases.FetchDogsUseCase
import daniel.estrada.dogswelove.presentation.home.DogsViewModel
import daniel.estrada.dogswelove.presentation.home.UiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class DogsViewModelTest {

    private lateinit var viewModel: DogsViewModel

    @Mock
    private lateinit var fetchDogsUseCase: FetchDogsUseCase

    @Before
    fun setup() {
        viewModel = DogsViewModel(fetchDogsUseCase)
    }

    @Test
    fun `data is fetched and ui state is updated correctly`() = runTest {
        val freshViewModel = DogsViewModel(fetchDogsUseCase)
        assertEquals(UiState(isLoading = false, error = null, dogs = emptyList()), freshViewModel.uiState.value)

        val fakeDogs = listOf(
            Dog(name = "Rex", description = "", age = 1, image = ""),
            Dog(name = "Spots", description = "", age = 2, image = ""),
            Dog(name = "Chief", description = "", age = 3, image = ""),
            Dog(name = "Boss", description = "", age = 4, image = ""),
        )
        whenever(fetchDogsUseCase()).thenReturn(fakeDogs)

        viewModel.uiState.test {
            var emission = awaitItem()
            assertEquals(UiState(isLoading = false, error = null, dogs = emptyList()), emission)
            emission = awaitItem()
            assertEquals(UiState(isLoading = true, error = null, dogs = emptyList()), emission)
            emission = awaitItem()
            assertEquals(UiState(isLoading = false, error = null, dogs = fakeDogs), emission)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `error is handled and ui state is updated correctly`() = runTest {
        val freshViewModel = DogsViewModel(fetchDogsUseCase)
        assertEquals(UiState(isLoading = false, error = null, dogs = emptyList()), freshViewModel.uiState.value)

        val message = "An error occurred"
        whenever(fetchDogsUseCase()).thenThrow(RuntimeException(message))

        viewModel.uiState.test {
            var emission = awaitItem()
            assertEquals(UiState(isLoading = false, error = null, dogs = emptyList()), emission)
            emission = awaitItem()
            assertEquals(UiState(isLoading = true, error = null, dogs = emptyList()), emission)
            emission = awaitItem()
            assertEquals(UiState(isLoading = false, error = message, dogs = emptyList()), emission)
            cancelAndIgnoreRemainingEvents()
        }
    }
}