package daniel.estrada.dogswelove.domain.repository

import daniel.estrada.dogswelove.domain.model.Dog

interface DogsRepository {
    suspend fun fetchDogsFromServer(): List<Dog>
    suspend fun fetchDogsFromDatabase(): List<Dog>
    suspend fun saveDogs(dogs: List<Dog>)
}

interface LocalDataSource {
    suspend fun getDogs(): List<Dog>
    suspend fun saveDogs(dogs: List<Dog>)
}

interface RemoteDataSource {
    suspend fun fetchDogs(): List<Dog>
}