package group.payback.domain.usecase

import group.payback.domain.repository.ImageRepository

class GetRemoteImageUseCase(private val imageRepository: ImageRepository) {
    operator fun invoke(keyword: String) = imageRepository.getRemoteImage(keyword)
}