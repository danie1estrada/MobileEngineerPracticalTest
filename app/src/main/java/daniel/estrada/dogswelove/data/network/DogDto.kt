package daniel.estrada.dogswelove.data.network


import com.google.gson.annotations.SerializedName
import daniel.estrada.dogswelove.domain.model.Dog

data class DogDto(
    @SerializedName("dogName")
    val name: String,
    val description: String,
    val age: Int,
    val image: String
)

fun DogDto.toDomain(): Dog = Dog(
    name = name,
    description = description,
    age = age,
    image = image
)