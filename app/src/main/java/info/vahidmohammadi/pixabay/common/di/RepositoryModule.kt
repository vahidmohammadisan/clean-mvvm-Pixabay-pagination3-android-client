package info.vahidmohammadi.pixabay.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.vahidmohammadi.data.repository.ImageRepositoryImpl
import info.vahidmohammadi.data.repository.dataSource.LocalImageDataSource
import info.vahidmohammadi.data.repository.dataSource.RemoteImageDataSource
import info.vahidmohammadi.domain.repository.ImageRepository

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