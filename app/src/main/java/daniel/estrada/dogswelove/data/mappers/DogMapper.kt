package daniel.estrada.dogswelove.data.mappers

import daniel.estrada.dogswelove.data.database.DogEntity
import daniel.estrada.dogswelove.data.network.DogDto
import daniel.estrada.dogswelove.domain.model.Dog

fun DogDto.toDomain(): Dog = Dog(
    name = name,
    description = description,
    age = age,
    image = image
)

fun DogEntity.toDomain(): Dog = Dog(
    name = name,
    description = description,
    age = age,
    image = image
)

fun Dog.toEntity(): DogEntity = DogEntity(
    name = name,
    description = description,
    age = age,
    image = image
)
