package daniel.estrada.dogswelove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import daniel.estrada.dogswelove.presentation.DogsScreen
import daniel.estrada.dogswelove.presentation.DogsViewModel
import daniel.estrada.dogswelove.ui.theme.DogsWeLoveTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsWeLoveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    val viewModel = hiltViewModel<DogsViewModel>()
                    val state = viewModel.uiState.collectAsStateWithLifecycle()
                    DogsScreen(state.value)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DogsWeLoveTheme {
        Greeting("Android")
    }
}