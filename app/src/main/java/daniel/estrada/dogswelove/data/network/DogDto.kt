package daniel.estrada.dogswelove.data.network


import com.google.gson.annotations.SerializedName

data class DogDto(
    @SerializedName("dogName")
    val name: String,
    val description: String,
    val age: Int,
    val image: String
)