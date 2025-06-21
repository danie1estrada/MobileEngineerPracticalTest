package daniel.estrada.dogswelove.data.repository

import daniel.estrada.dogswelove.data.mappers.toDomain
import daniel.estrada.dogswelove.data.network.DogService
import daniel.estrada.dogswelove.domain.model.Dog
import daniel.estrada.dogswelove.domain.repository.RemoteDataSource
import javax.inject.Inject

class RetrofitDataSource@Inject constructor(
    private val dogService: DogService
): RemoteDataSource {
    override suspend fun fetchDogs(): List<Dog> {
        return dogService.fetchDogs().map { it.toDomain() }
    }
}