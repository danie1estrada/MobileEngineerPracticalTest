package daniel.estrada.dogswelove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import daniel.estrada.dogswelove.presentation.home.DogsScreen
import daniel.estrada.dogswelove.presentation.home.DogsViewModel
import daniel.estrada.dogswelove.presentation.components.TopAppBar
import daniel.estrada.dogswelove.presentation.theme.DogsWeLoveTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsWeLoveTheme {
                val viewModel = hiltViewModel<DogsViewModel>()
                val state = viewModel.uiState.collectAsStateWithLifecycle()
                val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                    topBar = { TopAppBar(scrollBehavior) },
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                ) { paddingValues ->
                    DogsScreen(
                        modifier = Modifier.padding(paddingValues),
                        state = state.value,
                        showSnackBar = { message ->
                            showSnackBar(
                                message,
                                action = { viewModel.fetchDogs() },
                                snackBarHostState
                            )
                        }
                    )
                }
            }
        }
    }

    private fun showSnackBar(
        message: String,
        action: () -> Unit,
        snackBarHostState: SnackbarHostState
    ) {
        lifecycleScope.launch {
            val result = snackBarHostState.showSnackbar(
                message = message,
                actionLabel = getString(R.string.snacbark_action_retry),
                duration = SnackbarDuration.Indefinite
            )

            when (result) {
                SnackbarResult.ActionPerformed -> action()
                SnackbarResult.Dismissed -> snackBarHostState.currentSnackbarData?.dismiss()
            }
        }
    }
}