package info.vahidmohammadi.data.repository.dataSourceImpl

import info.vahidmohammadi.data.db.ImageDao
import info.vahidmohammadi.data.repository.dataSource.LocalImageDataSource
import info.vahidmohammadi.domain.model.Image
import kotlinx.coroutines.flow.Flow

class LocalImageDataSourceImpl(private val imageDao: ImageDao) : LocalImageDataSource {
    override fun getLocalImage(imageId: Int): Flow<Image> = imageDao.getImage(imageId)
}