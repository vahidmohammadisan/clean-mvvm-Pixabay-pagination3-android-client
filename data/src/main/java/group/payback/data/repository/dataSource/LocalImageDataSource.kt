package group.payback.data.repository.dataSource

import group.payback.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface LocalImageDataSource {
    fun getLocalImage(imageId: Int): Flow<Image>
}