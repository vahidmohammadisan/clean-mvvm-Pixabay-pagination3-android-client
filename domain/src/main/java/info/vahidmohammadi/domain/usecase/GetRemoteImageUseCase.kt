package info.vahidmohammadi.domain.usecase

import info.vahidmohammadi.domain.repository.ImageRepository

class GetRemoteImageUseCase(private val imageRepository: ImageRepository) {
    operator fun invoke(keyword: String) = imageRepository.getRemoteImage(keyword)
}