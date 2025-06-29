package daniel.estrada.dogswelove.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DogEntity::class], version = 1, exportSchema = false)
abstract class DogDatabase: RoomDatabase() {
    abstract fun dogDao(): DogDao
}