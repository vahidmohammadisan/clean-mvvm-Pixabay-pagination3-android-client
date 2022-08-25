package group.payback.pixabayclient.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import group.payback.data.api.ImageApi
import group.payback.data.db.ImageDB
import group.payback.data.repository.dataSource.RemoteImageDataSource
import group.payback.data.repository.dataSourceImpl.RemoteImageDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideRemoteDataSource(imageApi: ImageApi, imageDB: ImageDB): RemoteImageDataSource =
        RemoteImageDataSourceImpl(imageApi = imageApi, imageDB = imageDB)
}