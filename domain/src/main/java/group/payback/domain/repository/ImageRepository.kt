package group.payback.domain.repository

import androidx.paging.PagingData
import group.payback.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getRemoteImage(keyword: String): Flow<PagingData<Image>>
    fun getLocalImage(imageId: Int): Flow<Image>
}