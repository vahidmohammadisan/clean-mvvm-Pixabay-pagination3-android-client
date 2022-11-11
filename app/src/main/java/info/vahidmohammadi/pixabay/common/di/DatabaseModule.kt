package info.vahidmohammadi.pixabay.common.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.vahidmohammadi.data.db.ImageDB
import info.vahidmohammadi.data.db.ImageDao
import info.vahidmohammadi.data.db.RemoteImageKeysDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): ImageDB =
        Room.databaseBuilder(app, ImageDB::class.java, "image_db").fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideImageDao(imageDB: ImageDB): ImageDao = imageDB.imageDao()

    @Provides
    fun provideRemoteUmageKeysDao(imageDB: ImageDB): RemoteImageKeysDao =
        imageDB.remoteImageKeysDao()
}