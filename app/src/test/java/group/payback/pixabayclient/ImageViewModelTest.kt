package group.payback.pixabayclient

import group.payback.domain.usecase.ImageUseCases
import group.payback.pixabayclient.ui.image.ImageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ImageViewModelTest {

    lateinit var viewModel: ImageViewModel
    lateinit var useCases: ImageUseCases

    @Before
    fun setup() {
        viewModel = ImageViewModel(useCases)

    }
}
