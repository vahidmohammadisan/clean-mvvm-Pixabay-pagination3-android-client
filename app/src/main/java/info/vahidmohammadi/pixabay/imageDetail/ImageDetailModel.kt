package info.vahidmohammadi.pixabay.imageDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import info.vahidmohammadi.domain.model.Image
import info.vahidmohammadi.domain.usecase.ImageUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageDetailModel @Inject constructor(
    private val imageUseCases: ImageUseCases
) :
    ViewModel() {

    val imageDetails = MutableLiveData<Image>()

    fun getImageDetails(imageId: Int) {
        viewModelScope.launch {
            imageUseCases.getLocalImageUseCase.invoke(imageId = imageId).collect {
                withContext(Dispatchers.Main) {
                    imageDetails.postValue(it)
                }
            }
        }
    }

}