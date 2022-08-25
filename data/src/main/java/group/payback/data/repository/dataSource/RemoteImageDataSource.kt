package group.payback.data.repository.dataSource

import androidx.paging.PagingData
import group.payback.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface RemoteImageDataSource {
    fun getRemoteImage(keyword: String): Flow<PagingData<Image>>
}