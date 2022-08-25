package group.payback.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import group.payback.domain.model.RemoteImageKeys

@Dao
interface RemoteImageKeysDao {

    @Query("SELECT * FROM remote_image_keys WHERE id = :imageId")
    suspend fun getRemoteImageKeys(imageId: Int): RemoteImageKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteImageKeys(remoteImageKeys: List<RemoteImageKeys>)

    @Query("DELETE FROM remote_image_keys")
    suspend fun deleteAllRemoteImageKeys()
}