package daniel.estrada.dogswelove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import daniel.estrada.dogswelove.presentation.home.DogsScreen
import daniel.estrada.dogswelove.presentation.home.DogsViewModel
import daniel.estrada.dogswelove.presentation.components.TopAppBar
import daniel.estrada.dogswelove.presentation.theme.DogsWeLoveTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsWeLoveTheme {
                val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
                Scaffold(
                    topBar = { TopAppBar(scrollBehavior) },
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
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