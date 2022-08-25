package group.payback.domain.usecase

import group.payback.domain.repository.ImageRepository

class GetLocalImageUseCase(private val imageRepository: ImageRepository) {
    operator fun invoke(imageId: Int) = imageRepository.getLocalImage(imageId)
}