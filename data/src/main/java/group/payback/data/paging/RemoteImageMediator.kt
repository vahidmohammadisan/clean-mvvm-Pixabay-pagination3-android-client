package group.payback.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import group.payback.data.BuildConfig
import group.payback.data.api.ImageApi
import group.payback.data.db.ImageDB
import group.payback.domain.model.Image
import group.payback.domain.model.RemoteImageKeys

@OptIn(ExperimentalPagingApi::class)
class RemoteImageMediator(
    private val imageApi: ImageApi,
    private val imageDB: ImageDB,
    private var keyword: String
) :
    RemoteMediator<Int, Image>() {

    private val imageDao = imageDB.imageDao()
    private val remoteImageKeysDao = imageDB.remoteImageKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Image>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response =
                imageApi.getRemoteImages(apiKey = BuildConfig.API_KEY, keyword, page = page)
            var endOfPaginationReached = false
            if (response.isSuccessful) {
                val responseData = response.body()
                endOfPaginationReached = responseData == null
                responseData?.let {
                    imageDB.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            imageDao.deleteAllImages()
                            remoteImageKeysDao.deleteAllRemoteImageKeys()
                        }
                        var prevPage: Int?
                        var nextPage: Int

                        page.let { pageNumber ->
                            nextPage = pageNumber + 1
                            prevPage = if (pageNumber <= 1) null else pageNumber - 1
                        }

                        val keys = responseData.images.map { image ->
                            RemoteImageKeys(
                                id = image.id,
                                prevPage = prevPage,
                                nextPage = nextPage,
                                lastUpdated = System.currentTimeMillis()
                            )
                        }
                        remoteImageKeysDao.addAllRemoteImageKeys(remoteImageKeys = keys)
                        imageDao.addImage(image = responseData.images)
                    }
                }

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Image>,
    ): RemoteImageKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteImageKeysDao.getRemoteImageKeys(imageId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Image>,
    ): RemoteImageKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { image ->
                remoteImageKeysDao.getRemoteImageKeys(imageId = image.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Image>,
    ): RemoteImageKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { image ->
                remoteImageKeysDao.getRemoteImageKeys(imageId = image.id)
            }
    }
}