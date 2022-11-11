package info.vahidmohammadi.pixabay.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.vahidmohammadi.data.api.ImageApi
import info.vahidmohammadi.data.db.ImageDB
import info.vahidmohammadi.data.repository.dataSource.RemoteImageDataSource
import info.vahidmohammadi.data.repository.dataSourceImpl.RemoteImageDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideRemoteDataSource(imageApi: ImageApi, imageDB: ImageDB): RemoteImageDataSource =
        RemoteImageDataSourceImpl(imageApi = imageApi, imageDB = imageDB)
}