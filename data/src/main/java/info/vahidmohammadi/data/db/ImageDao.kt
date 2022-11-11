package info.vahidmohammadi.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.vahidmohammadi.domain.model.Image
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImage(image: List<Image>)

    @Query("SELECT * FROM Images")
    fun getAllImages(): PagingSource<Int, Image>

    @Query("SELECT * FROM Images WHERE id = :imageId")
    fun getImage(imageId: Int): Flow<Image>

    @Query("DELETE FROM Images")
    suspend fun deleteAllImages()
}