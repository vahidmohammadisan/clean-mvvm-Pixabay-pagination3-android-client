package info.vahidmohammadi.data.repository.dataSourceImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import info.vahidmohammadi.data.api.ImageApi
import info.vahidmohammadi.data.db.ImageDB
import info.vahidmohammadi.data.paging.RemoteImageMediator
import info.vahidmohammadi.data.repository.dataSource.RemoteImageDataSource
import info.vahidmohammadi.domain.model.Image
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