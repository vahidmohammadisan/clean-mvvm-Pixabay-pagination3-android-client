package group.payback.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import group.payback.domain.model.Image
import group.payback.domain.model.RemoteImageKeys

@Database(
    entities = [Image::class, RemoteImageKeys::class],
    version = 1,
    exportSchema = false
)
abstract class ImageDB : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun remoteImageKeysDao(): RemoteImageKeysDao
}