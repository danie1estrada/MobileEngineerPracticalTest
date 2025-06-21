package daniel.estrada.dogswelove.data.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import daniel.estrada.dogswelove.BuildConfig
import daniel.estrada.dogswelove.data.database.DogDao
import daniel.estrada.dogswelove.data.database.DogDatabase
import daniel.estrada.dogswelove.data.network.DogService
import daniel.estrada.dogswelove.data.repository.Repository
import daniel.estrada.dogswelove.data.repository.RetrofitDataSource
import daniel.estrada.dogswelove.data.repository.RoomDataSource
import daniel.estrada.dogswelove.domain.repository.DogsRepository
import daniel.estrada.dogswelove.domain.repository.LocalDataSource
import daniel.estrada.dogswelove.domain.repository.RemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDogService(
        retrofit: Retrofit
    ): DogService = retrofit.create(DogService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): DogDatabase = Room.databaseBuilder(
        context,
        DogDatabase::class.java,
        "dogs-db" // TODO: Get database name from string resources
    ).build()

    @Provides
    @Singleton
    fun provideDogDao(dogDatabase: DogDatabase): DogDao = dogDatabase.dogDao()

    @Provides
    fun bindRemoteDataSource(
        dogService: DogService
    ): RemoteDataSource = RetrofitDataSource(dogService)

    @Provides
    fun provideLocalDataSource(
        dogDao: DogDao
    ): LocalDataSource = RoomDataSource(dogDao)

    @Provides
    fun provideDogsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): DogsRepository = Repository(remoteDataSource, localDataSource)
}