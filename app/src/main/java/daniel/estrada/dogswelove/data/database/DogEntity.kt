package daniel.estrada.dogswelove.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import daniel.estrada.dogswelove.data.network.DogDto
import daniel.estrada.dogswelove.domain.model.Dog

@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey val name: String,
    val description: String,
    val age: Int,
    val image: String
) {
    companion object {
        fun fromDomain(dog: Dog) = DogEntity(
            name = dog.name,
            description = dog.description,
            age = dog.age,
            image = dog.image
        )

        fun fromDto(dogDto: DogDto) = DogEntity(
            name = dogDto.name,
            description = dogDto.description,
            age = dogDto.age,
            image = dogDto.image
        )
    }
}

fun DogEntity.toDomain(): Dog = Dog(
    name = name,
    description = description,
    age = age,
    image = image
)



