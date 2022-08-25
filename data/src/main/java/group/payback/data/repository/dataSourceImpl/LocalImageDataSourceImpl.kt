package group.payback.data.repository.dataSourceImpl

import group.payback.data.db.ImageDao
import group.payback.data.repository.dataSource.LocalImageDataSource
import group.payback.domain.model.Image
import kotlinx.coroutines.flow.Flow

class LocalImageDataSourceImpl(private val imageDao: ImageDao) : LocalImageDataSource {
    override fun getLocalImage(imageId: Int): Flow<Image> = imageDao.getImage(imageId)
}