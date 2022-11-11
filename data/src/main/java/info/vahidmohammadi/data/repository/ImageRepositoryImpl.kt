package info.vahidmohammadi.data.repository

import info.vahidmohammadi.data.repository.dataSource.LocalImageDataSource
import info.vahidmohammadi.data.repository.dataSource.RemoteImageDataSource
import info.vahidmohammadi.domain.model.Image
import info.vahidmohammadi.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

class ImageRepositoryImpl(
    private val remoteImageDataSource: RemoteImageDataSource,
    private val localImageDataSource: LocalImageDataSource
) : ImageRepository {
    override fun getRemoteImage(keyword: String) =
        remoteImageDataSource.getRemoteImage(keyword)

    override fun getLocalImage(imageId: Int): Flow<Image> =
        localImageDataSource.getLocalImage(imageId)
}