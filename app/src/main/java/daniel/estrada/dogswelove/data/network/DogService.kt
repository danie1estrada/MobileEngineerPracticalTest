package daniel.estrada.dogswelove.data.network

import retrofit2.http.GET

interface DogService {
    @GET("1151549092634943488")
    suspend fun fetchDogs(): List<DogDto>
}