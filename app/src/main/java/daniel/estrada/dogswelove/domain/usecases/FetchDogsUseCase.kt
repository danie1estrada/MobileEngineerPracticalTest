package daniel.estrada.dogswelove.domain.usecases

import daniel.estrada.dogswelove.domain.model.Dog
import daniel.estrada.dogswelove.domain.repository.DogsRepository
import javax.inject.Inject

class FetchDogsUseCase @Inject constructor(
    private val repository: DogsRepository
) {
    suspend operator fun invoke(): List<Dog> {
        val localDogs = repository.fetchDogsFromDatabase()
        if (localDogs.isNotEmpty()) {
            return localDogs
        }
        val remoteDogs = repository.fetchDogsFromServer()
        repository.saveDogs(remoteDogs)
        return remoteDogs
    }
}