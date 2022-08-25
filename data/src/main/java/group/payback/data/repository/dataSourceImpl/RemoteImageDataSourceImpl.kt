package group.payback.data.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import group.payback.data.api.ImageApi
import group.payback.data.db.ImageDB
import group.payback.data.paging.RemoteImageMediator
import group.payback.data.repository.dataSource.RemoteImageDataSource
import group.payback.domain.model.Image
import kotlinx.coroutines.flow.Flow

class RemoteImageDataSourceImpl(private val imageApi: ImageApi, private val imageDB: ImageDB) :
    RemoteImageDataSource {
    private val imageDao = imageDB.imageDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getRemoteImage(keyword: String): Flow<PagingData<Image>> {
        val pagingSourceFactory = { imageDao.getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = RemoteImageMediator(
                imageApi,
                imageDB,
                keyword
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}