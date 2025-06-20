package daniel.estrada.dogswelove.domain.repository

import daniel.estrada.dogswelove.domain.model.Dog

interface DogsRepository {
    fun fetchDogsFromServer(): List<Dog>
    fun fetchDogsFromDatabase(): List<Dog>
    fun saveDogs(dogs: List<Dog>)
}

interface LocalDataSource {
    fun getDogs(): List<Dog>
    fun saveDogs(dogs: List<Dog>)
}

interface RemoteDataSource {
    fun fetchDogs(): List<Dog>
}