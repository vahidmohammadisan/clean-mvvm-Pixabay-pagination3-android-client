package info.vahidmohammadi.pixabay

import info.vahidmohammadi.domain.usecase.ImageUseCases
import info.vahidmohammadi.pixabay.imageList.ImageListModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ImageViewModelTest {

    lateinit var viewModel: ImageListModel
    lateinit var useCases: ImageUseCases

    @Before
    fun setup() {
        viewModel = ImageListModel(useCases)

    }
}
