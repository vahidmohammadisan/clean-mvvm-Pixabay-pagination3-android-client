package info.vahidmohammadi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import info.vahidmohammadi.domain.model.Image
import info.vahidmohammadi.domain.model.RemoteImageKeys

@Database(
    entities = [Image::class, RemoteImageKeys::class],
    version = 1,
    exportSchema = false
)
abstract class ImageDB : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun remoteImageKeysDao(): RemoteImageKeysDao
}