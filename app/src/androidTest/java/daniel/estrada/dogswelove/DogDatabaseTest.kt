package daniel.estrada.dogswelove

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import daniel.estrada.dogswelove.data.database.DogDao
import daniel.estrada.dogswelove.data.database.DogDatabase
import daniel.estrada.dogswelove.data.mappers.toDomain
import daniel.estrada.dogswelove.data.mappers.toEntity
import daniel.estrada.dogswelove.domain.model.Dog
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class DogDatabaseTest {

    private lateinit var dogDao: DogDao
    private lateinit var db: DogDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DogDatabase::class.java).build()
        dogDao = db.dogDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun listOfDogsShouldBeInsertedAndRetrievedSuccessfully() = runTest {
        val dogs = listOf(
            Dog(name = "Rex", description = "", age = 1, image = ""),
            Dog(name = "Spots", description = "", age = 2, image = ""),
            Dog(name = "Chief", description = "", age = 3, image = ""),
            Dog(name = "Boss", description = "", age = 4, image = ""),
        )
        dogDao.insertAll(dogs.map { it.toEntity() })
        val dogsFromDb = dogDao.getAll()
        assert(dogsFromDb.containsAll(dogs.map { it.toEntity() }))
    }

    @Test
    @Throws(IOException::class)
    fun whenDuplicatedItemsAreInsertedExistingItemsShouldBeReplaced() = runTest {
        val originalList = listOf(
            Dog(name = "Rex", description = "", age = 1, image = ""),
            Dog(name = "Spots", description = "", age = 2, image = ""),
            Dog(name = "Chief", description = "", age = 3, image = ""),
            Dog(name = "Boss", description = "", age = 4, image = ""),
        )
        val dogWithDuplicatedId = Dog(name = "Krypto", description = "", age = 1, image = "")

        dogDao.insertAll(originalList.map { it.toEntity() })
        dogDao.insertAll(listOf(dogWithDuplicatedId.toEntity()))

        val dogWithDuplicatedIdFromDb = dogDao.getAll().first { it.age == 1 }
        assert(dogWithDuplicatedId == dogWithDuplicatedIdFromDb.toDomain())
    }
}