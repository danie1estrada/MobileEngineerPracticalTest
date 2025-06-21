package daniel.estrada.dogswelove.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import daniel.estrada.dogswelove.presentation.components.DogItem

@Composable
fun DogsScreen(
    modifier: Modifier = Modifier,
    state: UiState
) {
    Box (
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(state.dogs, key = { it.name }) { dog ->
                    DogItem(dog)
                }
            }
        }
    }
}



