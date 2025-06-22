package daniel.estrada.dogswelove

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import daniel.estrada.dogswelove.data.database.DogDao
import daniel.estrada.dogswelove.data.database.DogDatabase
import daniel.estrada.dogswelove.data.mappers.toDomain
import daniel.estrada.dogswelove.data.repository.Repository
import daniel.estrada.dogswelove.data.repository.RoomDataSource
import daniel.estrada.dogswelove.domain.model.Dog
import daniel.estrada.dogswelove.domain.repository.DogsRepository
import daniel.estrada.dogswelove.domain.repository.LocalDataSource
import daniel.estrada.dogswelove.domain.repository.RemoteDataSource
import daniel.estrada.dogswelove.domain.usecases.FetchDogsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FetchDogsUseCaseTest {
    private lateinit var fetchDogsUseCase: FetchDogsUseCase
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var repository: DogsRepository
    private lateinit var db: DogDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DogDatabase::class.java).build()
        remoteDataSource = FakeRemoteDataSource()
        localDataSource = RoomDataSource(db.dogDao())
        repository = Repository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
        fetchDogsUseCase = FetchDogsUseCase(repository)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun dataShouldBeFetchedFromEndpointAndSavedToDatabase() = runTest {
        // Assert that the database is empty
        assert(localDataSource.getDogs().isEmpty())

        // Assert that the data was fetched from the remote data source
        val dataFetchedFromEndpoint = fetchDogsUseCase()
        assert(dataFetchedFromEndpoint.containsAll(remoteDataSource.fetchDogs()))

        // Assert that the data was saved to the database
        val dataSavedInDb = localDataSource.getDogs()
        assert(dataFetchedFromEndpoint.containsAll(dataSavedInDb))
    }
}

class FakeRemoteDataSource : RemoteDataSource {
    override suspend fun fetchDogs(): List<Dog> = listOf(
        Dog(name = "Rex", description = "", age = 1, image = ""),
        Dog(name = "Spots", description = "", age = 2, image = ""),
        Dog(name = "Chief", description = "", age = 3, image = ""),
        Dog(name = "Boss", description = "", age = 4, image = ""),
    )
}