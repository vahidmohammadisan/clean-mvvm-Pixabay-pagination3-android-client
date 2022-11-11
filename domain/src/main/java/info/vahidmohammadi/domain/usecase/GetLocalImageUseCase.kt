package info.vahidmohammadi.domain.usecase

import info.vahidmohammadi.domain.repository.ImageRepository

class GetLocalImageUseCase(private val imageRepository: ImageRepository) {
    operator fun invoke(imageId: Int) = imageRepository.getLocalImage(imageId)
}