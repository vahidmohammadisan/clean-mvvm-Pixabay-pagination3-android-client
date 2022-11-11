package info.vahidmohammadi.data.repository.dataSource

import info.vahidmohammadi.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface LocalImageDataSource {
    fun getLocalImage(imageId: Int): Flow<Image>
}