package group.payback.data.repository

import group.payback.data.repository.dataSource.LocalImageDataSource
import group.payback.data.repository.dataSource.RemoteImageDataSource
import group.payback.domain.model.Image
import group.payback.domain.repository.ImageRepository
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