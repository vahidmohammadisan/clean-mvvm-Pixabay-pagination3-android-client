package info.vahidmohammadi.pixabay.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.vahidmohammadi.domain.repository.ImageRepository
import info.vahidmohammadi.domain.usecase.GetLocalImageUseCase
import info.vahidmohammadi.domain.usecase.GetRemoteImageUseCase
import info.vahidmohammadi.domain.usecase.ImageUseCases

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideImageUseCases(imageRepository: ImageRepository) = ImageUseCases(
        getRemoteImageUseCase = GetRemoteImageUseCase(imageRepository = imageRepository),
        getLocalImageUseCase = GetLocalImageUseCase(imageRepository = imageRepository),
    )
}