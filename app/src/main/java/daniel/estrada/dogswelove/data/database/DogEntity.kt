package daniel.estrada.dogswelove.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey val name: String,
    val description: String,
    val age: Int,
    val image: String
)