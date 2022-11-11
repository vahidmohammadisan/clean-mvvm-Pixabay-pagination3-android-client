package group.payback.pixabayclient.ui.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import group.payback.domain.model.Image
import group.payback.domain.usecase.ImageUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageUseCases: ImageUseCases
) :
    ViewModel() {

    val image = MutableLiveData<PagingData<Image>>()

    fun searchImage(keyword: String) {
        CoroutineScope(Dispatchers.Default).launch {
            imageUseCases.getRemoteImageUseCase.invoke(keyword = keyword).collect {
                withContext(Dispatchers.Main) {
                    image.postValue(it)
                }
            }
        }
    }

    val imageDetails = MutableLiveData<Image>()

    fun getImageDetails(imageId: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            imageUseCases.getLocalImageUseCase.invoke(imageId = imageId).collect {
                withContext(Dispatchers.Main) {
                    imageDetails.postValue(it)
                }
            }
        }
    }

}