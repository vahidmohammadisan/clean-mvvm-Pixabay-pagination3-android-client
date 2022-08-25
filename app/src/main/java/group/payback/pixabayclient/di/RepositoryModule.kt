package group.payback.pixabayclient.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import group.payback.data.repository.ImageRepositoryImpl
import group.payback.data.repository.dataSource.LocalImageDataSource
import group.payback.data.repository.dataSource.RemoteImageDataSource
import group.payback.domain.repository.ImageRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideImageRepository(
        remoteImageDataSource: RemoteImageDataSource,
        localImageDataSource: LocalImageDataSource
    ): ImageRepository =
        ImageRepositoryImpl(
            remoteImageDataSource = remoteImageDataSource,
            localImageDataSource = localImageDataSource,
        )
}