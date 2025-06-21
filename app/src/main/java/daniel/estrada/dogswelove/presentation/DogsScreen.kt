package daniel.estrada.dogswelove.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import daniel.estrada.dogswelove.domain.model.Dog

@Composable
fun DogsScreen(
    state: UiState
) {
    Box {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(state.dogs, key = { it.name }) { dog ->
                    DogItem(dog)
                }
            }
        }
    }
}

@Composable
fun DogItem(
    dog: Dog
) {
    Row {
        AsyncImage(
            model = dog.image,
            contentDescription = null
        )

        Column {
            Text(text = dog.name)
            Text(text = dog.description)
            Text(text = "${dog.age}")
        }
    }
}