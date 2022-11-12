package info.vahidmohammadi.pixabay.imageList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import info.vahidmohammadi.domain.model.Image
import info.vahidmohammadi.domain.usecase.ImageUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageListModel @Inject constructor(
    private val imageUseCases: ImageUseCases
) :
    ViewModel() {

    val image = MutableLiveData<PagingData<Image>>()

    fun searchImage(keyword: String) {
        viewModelScope.launch {
            imageUseCases.getRemoteImageUseCase.invoke(keyword = keyword).collect {
                withContext(Dispatchers.Main) {
                    image.postValue(it)
                }
            }
        }
    }

}