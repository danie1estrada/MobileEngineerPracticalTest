package daniel.estrada.dogswelove.data.repository

import daniel.estrada.dogswelove.data.database.DogDao
import daniel.estrada.dogswelove.data.mappers.toDomain
import daniel.estrada.dogswelove.data.mappers.toEntity
import daniel.estrada.dogswelove.domain.model.Dog
import daniel.estrada.dogswelove.domain.repository.LocalDataSource
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val dogDao: DogDao
) : LocalDataSource {
    override suspend fun getDogs(): List<Dog> {
        return dogDao.getAll().map { it.toDomain() }
    }

    override suspend fun saveDogs(dogs: List<Dog>) {
        dogDao.insertAll(dogs.map { it.toEntity() })
    }
}