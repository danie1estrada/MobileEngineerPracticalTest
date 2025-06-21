package daniel.estrada.dogswelove.data.repository

import daniel.estrada.dogswelove.domain.model.Dog
import daniel.estrada.dogswelove.domain.repository.DogsRepository
import daniel.estrada.dogswelove.domain.repository.LocalDataSource
import daniel.estrada.dogswelove.domain.repository.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): DogsRepository {
    override suspend fun fetchDogsFromServer(): List<Dog> {
        return remoteDataSource.fetchDogs()
    }

    override suspend fun fetchDogsFromDatabase(): List<Dog> {
        return localDataSource.getDogs()
    }

    override suspend fun saveDogs(dogs: List<Dog>) {
        localDataSource.saveDogs(dogs)
    }
}