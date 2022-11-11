package info.vahidmohammadi.data.repository.dataSource

import androidx.paging.PagingData
import info.vahidmohammadi.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface RemoteImageDataSource {
    fun getRemoteImage(keyword: String): Flow<PagingData<Image>>
}