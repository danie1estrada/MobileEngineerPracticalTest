package daniel.estrada.dogswelove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import daniel.estrada.dogswelove.presentation.DogsScreen
import daniel.estrada.dogswelove.presentation.DogsViewModel
import daniel.estrada.dogswelove.presentation.components.TopAppBar
import daniel.estrada.dogswelove.ui.theme.DogsWeLoveTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsWeLoveTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar() }
                ) { paddingValues ->
                    val viewModel = hiltViewModel<DogsViewModel>()
                    val state = viewModel.uiState.collectAsStateWithLifecycle()
                    DogsScreen(
                        modifier = Modifier.padding(paddingValues),
                        state = state.value
                    )
                }
            }
        }
    }
}