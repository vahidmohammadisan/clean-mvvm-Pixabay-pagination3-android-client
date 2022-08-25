package group.payback.pixabayclient.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import group.payback.data.db.ImageDao
import group.payback.data.repository.dataSource.LocalImageDataSource
import group.payback.data.repository.dataSourceImpl.LocalImageDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(imageDao: ImageDao): LocalImageDataSource =
        LocalImageDataSourceImpl(imageDao = imageDao)
}