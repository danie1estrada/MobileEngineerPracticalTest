package daniel.estrada.dogswelove.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import daniel.estrada.dogswelove.domain.model.Dog

@Composable
fun DogItem(
    dog: Dog
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        AsyncImage(
            model = dog.image,
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                .width(125.dp)
        )

        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors().copy(containerColor = Color.White),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 16.dp,
                bottomEnd = 16.dp,
                bottomStart = 0.dp
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = dog.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF333333)
                )
                Text(
                    text = dog.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666)
                )
                Text(
                    text = "Almost ${dog.age} years",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF333333),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun DogItemPreview() {
    DogItem(dog = Dog(
        name = "Krypto",
        description = "Description",
        age = 1,
        image = "https://static.wikia.nocookie.net/isle-of-dogs/images/a/af/Rex.jpg/revision/latest/scale-to-width-down/666?cb=20180625001634"
    ))
}