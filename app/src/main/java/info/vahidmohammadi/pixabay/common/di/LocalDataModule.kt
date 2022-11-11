package info.vahidmohammadi.pixabay.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.vahidmohammadi.data.db.ImageDao
import info.vahidmohammadi.data.repository.dataSource.LocalImageDataSource
import info.vahidmohammadi.data.repository.dataSourceImpl.LocalImageDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(imageDao: ImageDao): LocalImageDataSource =
        LocalImageDataSourceImpl(imageDao = imageDao)
}