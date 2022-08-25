package group.payback.pixabayclient.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import group.payback.domain.repository.ImageRepository
import group.payback.domain.usecase.GetLocalImageUseCase
import group.payback.domain.usecase.GetRemoteImageUseCase
import group.payback.domain.usecase.ImageUseCases

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideImageUseCases(imageRepository: ImageRepository) = ImageUseCases(
        getRemoteImageUseCase = GetRemoteImageUseCase(imageRepository = imageRepository),
        getLocalImageUseCase = GetLocalImageUseCase(imageRepository = imageRepository),
    )
}