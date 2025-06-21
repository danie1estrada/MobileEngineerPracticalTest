package daniel.estrada.dogswelove.data.network

import retrofit2.http.GET

interface DogService {
    @GET("") // TODO: Add endpoint
    suspend fun fetchDogs(): List<DogDto>
}