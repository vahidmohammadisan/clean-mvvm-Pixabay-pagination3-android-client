package info.vahidmohammadi.domain.repository

import androidx.paging.PagingData
import info.vahidmohammadi.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getRemoteImage(keyword: String): Flow<PagingData<Image>>
    fun getLocalImage(imageId: Int): Flow<Image>
}